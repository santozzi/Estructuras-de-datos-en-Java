package TDAGrafo.Implementaciones.Graph;

import java.util.Iterator;

import TDAGrafo.Graph;
import TDAGrafo.Arcos.Arco;
import TDAGrafo.Arcos.Edge;
import TDAGrafo.Excepciones.InvalidEdgeException;
import TDAGrafo.Excepciones.InvalidVertexException;
import TDAGrafo.Vertices.Vertex;
import TDAGrafo.Vertices.Vertice;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.PositionList;

public class ListaDeArcosGraph<V, E> implements Graph<V, E> {
	protected PositionList<Vertice<V>> nodos;
	protected PositionList<Arco<V,E>> arcos;

	public ListaDeArcosGraph() {
		nodos = new DoubleLinkedList<Vertice<V>>();
		arcos = new DoubleLinkedList<Arco<V,E>>();
	}

	
	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista =  new DoubleLinkedList<Vertex<V>>();
		for(Vertex<V> v : nodos) {
			lista.addLast(v);
		}
		return lista;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista =  new DoubleLinkedList<Edge<E>>();
		for(Edge<E> a : arcos) {
			lista.addLast(a);
		}
		return lista;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		Vertice<V> nodo = checkVertex(v);
		PositionList<Edge<E>> incidentes = new DoubleLinkedList<Edge<E>>();
		
		for(Arco<V,E> arco : arcos) {
			if(arco.getV2()==nodo || arco.getV1()==nodo) {
				incidentes.addLast(arco);
			}
		}
		return incidentes;
	}
	
	
	
	protected Vertice<V> checkVertex(Vertex<V> v)throws InvalidVertexException{
		Vertice<V> nodo = null;
		if(v==null ||nodos.isEmpty()) {
			throw new InvalidVertexException("Vertice invalido");
		}else {
			try {
				nodo = (Vertice<V>)v;
			}catch(ClassCastException e) {
				throw new InvalidVertexException("error de casteo");
			}

		}
		return nodo	;
	}


	@SuppressWarnings("unchecked")
	protected Arco<V,E> checkEdge(Edge<E> e)throws InvalidEdgeException{
		Arco<V,E> arco = null;
		if(e==null ||arcos.isEmpty()) {
			throw new InvalidEdgeException("Arco invalido");
		}else {
			try {
				arco = (Arco<V,E>)e;
			}catch(ClassCastException e1) {
				throw new InvalidEdgeException("error de casteo");
			}
          if(arco.getV1()==null||arco.getV2()==null) {
        	  throw new InvalidEdgeException("arco incompleto");
          }
		}
		return arco	;
	}
	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		Vertice<V> nodo = checkVertex(v);
		Arco<V,E> arco = checkEdge(e);
		Vertice<V> aRetornar=null;
		
		if(arco.getV1()==nodo) {
			aRetornar= arco.getV2();
		}else if(arco.getV2()==nodo){
			aRetornar= arco.getV1();
		}else {
			throw new InvalidEdgeException("arco invalido");
		}
		
		return aRetornar;
	}
    @SuppressWarnings("unchecked")
	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> arco = checkEdge(e);
		
		Vertex<V>[] arreglo = (Vertex<V>[]) new Vertex[2];
		arreglo[0]=arco.getV1();
		arreglo[1]=arco.getV2();
		
		return arreglo;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		Vertice<V> v1 = checkVertex(v);
		Vertice<V> v2 = checkVertex(w);
		boolean corte= false;
		
		Iterator<Arco<V,E>> iteradorDeArcos = arcos.iterator();
		
    	while(iteradorDeArcos.hasNext()&&!corte) {
			Arco<V,E> arco = iteradorDeArcos.next();
			
			if(arco.getV1()==v1) {
				corte=arco.getV2()==v2;
			}else if(arco.getV1()==v2) {
				corte=arco.getV2()==v1;
			}
		}
		return corte;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice<V> nodo = checkVertex(v);
		V aRetornar = nodo.getRotulo();
		nodo.setRotulo(x);
		return aRetornar;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		
		
		Vertice<V> nuevo = new Vertice<V>(x);
		nodos.addLast(nuevo);
		
		try {
		  nuevo.setPos(nodos.last());	
		}catch(EmptyListException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return nuevo;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		Vertice<V> v1 = checkVertex(v);
		Vertice<V> v2 = checkVertex(w);
		Arco<V,E> nuevo = new Arco<V,E>(v1,v2,e);
		
		arcos.addLast(nuevo);
		
		try {
			nuevo.setPos(arcos.last());
		} catch (EmptyListException e1) {
			System.out.println(e1.getMessage());
		}
		return nuevo;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V> nodo = checkVertex(v);
		V aRetornar = nodo.element();
		for(Arco<V,E> arco : arcos) {
			if(arco.getV1()==nodo||arco.getV2()==nodo) {
				eliminarArco(arco);
				
			}
		}
		try {
			nodos.remove(nodo.getPos());
			nodo.setRotulo(null);
			nodo.setPos(null);
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
		
		
		return aRetornar;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> arco = checkEdge(e);
		
		
		
		
		return eliminarArco(arco);
	}
	private E eliminarArco(Arco<V,E> arco) {
	      E aRetornar= null;
		   arco.setV1(null);
	       arco.setV2(null);
	       try {
	    	   aRetornar = arcos.remove(arco.getPos()).getRotulo();
	    	   arco.setPos(null);
	    	   arco.setRotulo(null);
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
	     
	    return aRetornar;  
	}

}
