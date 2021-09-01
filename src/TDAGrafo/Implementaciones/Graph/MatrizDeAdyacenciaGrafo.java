package TDAGrafo.Implementaciones.Graph;

import TDACola.EmptyQueueException;
import TDACola.LinkedQueue;
import TDACola.Queue;
import TDAGrafo.Graph;
import TDAGrafo.Arcos.ArcoI;
import TDAGrafo.Arcos.Edge;
import TDAGrafo.Excepciones.InvalidEdgeException;
import TDAGrafo.Excepciones.InvalidVertexException;
import TDAGrafo.Vertices.Vertex;
import TDAGrafo.Vertices.VerticeI;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.PositionList;

public class MatrizDeAdyacenciaGrafo<V, E> implements Graph<V, E> {
	protected PositionList<VerticeI<V>> nodos;
	protected PositionList<ArcoI<V,E>> arcos;
	protected ArcoI<V,E>[][] adyacentes;
	protected int cantidadDeVertices;
	protected Queue<Integer> indicesDisponibles;
	

	@SuppressWarnings("unchecked")
	public MatrizDeAdyacenciaGrafo() {
	   final int tamInicial = 11;
	   this.nodos = new DoubleLinkedList<VerticeI<V>>();	
	   this.arcos = new DoubleLinkedList<ArcoI<V,E>>();
       this.adyacentes = (ArcoI<V,E>[][])new ArcoI[tamInicial][tamInicial];
       this.indicesDisponibles = new LinkedQueue<Integer>();
       this.cantidadDeVertices=0;
 	}
	
	
	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista = new DoubleLinkedList<Vertex<V>>();
		for(Vertex<V> nodo : nodos) {
			lista.addLast(nodo);
		}
		return lista;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new DoubleLinkedList<Edge<E>>();
		for(Edge<E> ArcoI : arcos) {
			lista.addLast(ArcoI);
		}
		return lista;
	}
	
	

	
	
	@SuppressWarnings("unchecked")
	protected ArcoI<V,E> checkEdge(Edge<E> e)throws InvalidEdgeException{
		ArcoI<V,E> ArcoI = null;
		if(e==null ||arcos.isEmpty()) {
			throw new InvalidEdgeException("ArcoI invalido");
		}else {
			try {
				ArcoI = (ArcoI<V,E>)e;
			}catch(ClassCastException e1) {
				throw new InvalidEdgeException("error de casteo");
			}
          if(ArcoI.getV1()==null||ArcoI.getV2()==null) {
        	  throw new InvalidEdgeException("ArcoI incompleto");
          }
		}
		return ArcoI	;
	}
	
	protected VerticeI<V> checkVertex(Vertex<V> v)throws InvalidVertexException{
		VerticeI<V> nodo = null;
		if(v==null ||nodos.isEmpty()) {
			throw new InvalidVertexException("Vertice invalido");
		}else {
			try {
				nodo = (VerticeI<V>)v;
			}catch(ClassCastException e) {
				throw new InvalidVertexException("error de casteo");
			}

		}
		return nodo	;
	}
	
	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		PositionList<Edge<E>> lista= new DoubleLinkedList<Edge<E>>();
		VerticeI<V> nodo = checkVertex(v);
		//asumo que el ArcoI esta bien cargado, y como el grafo es no dirigido, no cargo su simétrico. ej, (1,2) (2,1)
		int indice = nodo.getIndice();
		for(int i=0; i<adyacentes.length;i++) {
			ArcoI<V,E> ArcoI = adyacentes[indice][i];
			if(ArcoI!=null) {
				lista.addLast(ArcoI);
			}
		}
		return lista;
	}


	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		VerticeI<V> nodo = checkVertex(v);
		ArcoI<V,E> ArcoI = checkEdge(e);
		//int indice = nodo.getIndice();
		Vertex<V> opuesto;
		if(ArcoI.getV1()==nodo) {
			opuesto= ArcoI.getV2();
		}else if(ArcoI.getV2()==nodo) {
			opuesto= ArcoI.getV1();
		}else {
			opuesto = null;
			throw new InvalidEdgeException("El ArcoI no corresponde al grafo");
		}
			
		return opuesto;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		ArcoI<V,E> ArcoI = checkEdge(e);
		@SuppressWarnings("unchecked")
		Vertex<V>[] aRetornar =  (Vertex<V>[])new Vertex[2];
		aRetornar[0]= ArcoI.getV1();
		aRetornar[1]= ArcoI.getV2();
		return aRetornar;
	}

	
	/**
	 * Ojo que cambia para grafo dirigido
	 * chequeo solo de un lado ya que no es un grafo dirigido
	 */
	
	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		VerticeI<V> v1 = checkVertex(v);
		VerticeI<V> v2 = checkVertex(w);
		boolean sonAdyacentes = false;
		
		int indiceV1 = v1.getIndice();
		for(int i= 0; i<adyacentes.length&&!sonAdyacentes;i++) {
			ArcoI<V,E> ArcoI = adyacentes[indiceV1][i];
			if(ArcoI!=null) {
				if(ArcoI.getV1()==v1) {
					sonAdyacentes = ArcoI.getV2()==v2;
				}else if(ArcoI.getV2()==v1) {
					sonAdyacentes = ArcoI.getV1()==v2;
				}
			}
		}
		return sonAdyacentes;
	}
    
	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		VerticeI<V> nodo = checkVertex(v);
		V aRetornar = v.element();
		nodo.setRotulo(x);
		
		return aRetornar;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		VerticeI<V> nuevo = new VerticeI<V>(x);
		nodos.addLast(nuevo);
		try {
			nuevo.setPosVerticeI(nodos.last());
			if(cantidadDeVertices==adyacentes.length) {
				resize();
			}
			
			//cantidadDeVertices no hace lo que dice. cambiar nombre.
			if(indicesDisponibles.isEmpty()) {
			   nuevo.setIndice(cantidadDeVertices++);
			}else {
			   try {
				nuevo.setIndice(indicesDisponibles.dequeue());
			} catch (EmptyQueueException e) {
				System.out.println(e.getMessage());
			}
			}
						
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		} 
		return nuevo;
	}

	@SuppressWarnings("unchecked")
	protected void resize() {
		final int tam =  adyacentes.length*2;  
		
		ArcoI<V,E>[][] adyacentesnuevo = (ArcoI<V,E>[][])new ArcoI[tam][tam];	
		for(int i= 0; i<adyacentes.length;i++) {
			for(int j= 0; j<adyacentes[0].length;j++) {
				adyacentesnuevo[i][j] = adyacentes[i][j];
			}
		}
		adyacentes= adyacentesnuevo;
	}

    /**
     * Ojo en grafo dirigido cambia
     */
	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		VerticeI<V> v1 = checkVertex(v);
		VerticeI<V> v2 = checkVertex(w);
		ArcoI<V,E> nuevo = new ArcoI<V,E>(v1,v2,e);
		arcos.addLast(nuevo);
		try {
			nuevo.setPos(arcos.last());
			adyacentes[v1.getIndice()][v2.getIndice()] = nuevo;
			adyacentes[v2.getIndice()][v1.getIndice()] = nuevo;
			
			
		} catch (EmptyListException e1) {
			System.out.println(e1.getMessage());
		}
		return nuevo;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		VerticeI<V> nodo = checkVertex(v);
		V aRetornar = nodo.element();
		int indice = nodo.getIndice();
		for(int i=0 ; i< adyacentes.length;i++) {
			ArcoI<V,E> arco = adyacentes[indice][i];
			if(arco!=null) {
				eliminarArcoI(arco);
				indicesDisponibles.enqueue(indice);
			}
			
		}
		try {
			nodos.remove(nodo.getPosVerticeI());
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
		this.cantidadDeVertices--;
		return aRetornar;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		ArcoI<V,E> ArcoI = checkEdge(e);
		E aRetornar = eliminarArcoI(ArcoI);
		return aRetornar;
	}
	protected E eliminarArcoI(ArcoI<V,E> arco) {
		E aRetornar = arco.element();
	    int indiceV1 = arco.getV1().getIndice();
	    int indiceV2 = arco.getV2().getIndice();
	    //elimino de la matriz de adyacentes
	    adyacentes[indiceV1][indiceV2]=null;
	    adyacentes[indiceV2][indiceV1]=null;
	    
	    //elimino de la lista de arcos
	    try {
			arcos.remove(arco.getPos());
			//limpo el arco
			arco.setPos(null);
			arco.setRotulo(null);
			arco.setV1(null);
			arco.setV2(null);
			arco=null;
			
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
	    
	    
		return aRetornar;	
	}

	
	
	
	
	
	
	
	
	
	

}
