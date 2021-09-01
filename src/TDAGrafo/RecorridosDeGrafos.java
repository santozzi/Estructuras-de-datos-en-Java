package TDAGrafo;

import java.util.Iterator;

import TDACola.EmptyQueueException;
import TDACola.LinkedQueue;
import TDACola.Queue;
import TDAGrafo.Arcos.Edge;
import TDAGrafo.Excepciones.InvalidEdgeException;
import TDAGrafo.Excepciones.InvalidVertexException;
import TDAGrafo.Vertices.Vertex;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.PositionList;
import TDAMap.Entry;
import TDAMap.InvalidKeyException;
import TDAMap.Map;
import TDAMap.OpenHashMap;

public class RecorridosDeGrafos {
	public static final Object VISITADO = new Object();
	public static final Object NOVISITADO = new Object();
	public static final Object ESTADO = new Object();



	public static <V,E> void DFS (Graph<V,E> g) {
		Map<Vertex<V>,Boolean> visita = new OpenHashMap<Vertex<V>, Boolean>();

		for(Vertex<V> nodo : g.vertices()) {
			try {
				visita.put(nodo, false);
			} catch (InvalidKeyException e) {
				System.out.println(e.getMessage());
			}
		}

		for(Vertex<V> nodo : g.vertices()) {
			try {
				if(visita.get(nodo)==false) {
					DFSRecu(g,nodo,visita);
				}
			} catch (InvalidKeyException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println();

	}

	private static <V,E> void DFSRecu (Graph<V,E> g,Vertex<V> nodo, 
			Map<Vertex<V>,Boolean> visita) {

		try {
			System.out.print(nodo.element()+ " - ");
			visita.put(nodo, true);
			for(Edge<E> arco : g.incidentEdges(nodo)) {
				Vertex<V> v = g.opposite(nodo, arco);
				if(!visita.get(v)) {
					DFSRecu(g,v,visita);
				}
			}


		} catch (InvalidKeyException |
				InvalidVertexException |
				InvalidEdgeException e) {

			System.out.println(e.getLocalizedMessage());
		}


	}


	public static <V,E> boolean hallarCamino(Graph<V,E>  g,
			Vertex<V> origen, Vertex<V> destino, PositionList<Vertex<V>> camino) {

		try {
			origen.put(ESTADO, VISITADO);
			camino.addLast(origen);
			if(origen==destino) {

				return true;
			}else {

				for(Edge<E> adyacente : g.incidentEdges(origen) ) {
					Vertex<V> opuesto = g.opposite(origen, adyacente);
					if(opuesto.get(ESTADO)==null) {
						boolean encontre = hallarCamino(g, opuesto, destino, camino);
						if(encontre) {
							return true;
						}
					}

				}
				camino.remove(camino.last());
				return false;

			}





		} catch (InvalidKeyException | InvalidVertexException |
				InvalidEdgeException |InvalidPositionException |
				EmptyListException e) {
			System.out.println(e.getMessage());

		}

		return false;
	}


	public static <V,E> PositionList<Vertex<V>> hallarCiclo(Graph<V,E>  g, Vertex<V> origen){

		boolean encontre = false;
		PositionList<Vertex<V>> camino = new DoubleLinkedList<Vertex<V>>();
		try {
			Iterator<Edge<E>> adyacentes = g.incidentEdges(origen).iterator();
			while(!encontre&&adyacentes.hasNext()) {
				Vertex<V> opuesto = g.opposite(origen, adyacentes.next());
				encontre = hallarCamino(g,opuesto,origen,camino);

			}

		}catch(InvalidVertexException | InvalidEdgeException e) {
			System.out.println(e.getMessage());
		}
		PositionList<Vertex<V>> ciclo = new DoubleLinkedList<Vertex<V>>();
		if(encontre) {

			ciclo.addLast(origen);
			for(Vertex<V> nodo : camino) {
				ciclo.addLast(nodo);
			}
		}
		return ciclo;

	}

	public static <V,E> void BFS(Graph<V,E> g, Vertex<V> origen,PositionList<Vertex<V>> camino){
		Queue<Vertex<V>> cola = new LinkedQueue<Vertex<V>>();
		cola.enqueue(origen);
		try {
			origen.put(ESTADO, VISITADO);

			while(!cola.isEmpty()) {
				Vertex<V> u= cola.dequeue();
				camino.addLast(u);
				for(Edge<E> adyacente : g.incidentEdges(u)) {
					Vertex<V> opuesto = g.opposite(u, adyacente);
					if(opuesto.get(ESTADO)==null) {
						opuesto.put(ESTADO, VISITADO);
						cola.enqueue(opuesto);

					}
				}

			}


		} catch (InvalidKeyException | EmptyQueueException |
				InvalidVertexException | InvalidEdgeException e) {
			System.out.println(e.getMessage());
		}

	}


	@SuppressWarnings("hiding")
	public static <V,Integer> void hallarCaminoMinimo(Graph<V,Integer> g, Vertex<V>origen, Vertex<V> destino,
			ResultadoCamino<V>actual, ResultadoCamino<V> minimo)
	{

		try {
			origen.put(ESTADO, VISITADO);
			actual.getCamino().addLast(origen);

			if(origen==destino) {


				if(actual.getCosto()<minimo.getCosto()) {
					minimo.setCosto(actual.getCosto());
					minimo.copiarLista(actual.getCamino());
				}
			}else {
				for(Edge<Integer> adyacente : g.incidentEdges(origen)) {
					Vertex<V> opuesto = g.opposite(origen, adyacente);
					if(opuesto.get(ESTADO)==null) {
						actual.setCosto(actual.getCosto()+(int)adyacente.element());
						hallarCaminoMinimo(g,opuesto,destino,actual,minimo);
					}
				}

			}


			actual.getCamino().remove(actual.getCamino().last());
			origen.put(ESTADO, null);



		} catch (InvalidKeyException | InvalidVertexException | InvalidEdgeException | EmptyListException |
				InvalidPositionException e) {
			System.out.println(e.getMessage());
		}


	}

	//Integer >0
	@SuppressWarnings("hiding")
	/**
	 * Devuelve caminos minimos
	 * @param <V> Tipo de elmento de vertice
	 * @param <E> Tipo de elemento de arcos
	 * @param g Grafo dirigido
	 * @param origen Vertice de origen
	 * @param d Mapeo de tipo Float para los costos de los caminos
	 * @param p Mapeo de el Vertice anterior
	 * @param s Mapeo para marcar visitado
	 */
	public static <V,E> void dijkstra(GraphD<V,Float> g, Vertex<V> origen,
			Map<Vertex<V>,Float> d, Map<Vertex<V>,Vertex<V>> p, Map<Vertex<V>,Boolean> s) {
		try {
			//lleno los mapeso con los vertices
			for(Vertex<V> nodo : g.vertices()) {

				d.put(nodo,Float.MAX_VALUE);
				p.put(nodo, null);

			}

			d.put(origen,0f);





			int size = d.size();
			for(int i=0; i< size; i++) {
				float minimo =Float.MAX_VALUE;
				Vertex<V> minimoV =null ;
				for(Entry<Vertex<V>,Float> entrada : d.entries()) {

					float peso = (float)entrada.getValue();

					if(peso<minimo && (s.get(entrada.getKey())==null)) {
						minimo=peso;
						minimoV= entrada.getKey();
					}
				}
				if(minimoV!=null) {
					s.put(minimoV, true);
					for(Edge<Float> adyacente: g.succesorEdges(minimoV)) {
						Vertex<V> opuesto = g.opposite(minimoV, adyacente);
						if((d.get(minimoV)+ adyacente.element())<d.get(opuesto)) {
							d.put(opuesto,d.get(minimoV)+ adyacente.element());
							System.out.println(opuesto.element()+" - "+ minimoV.element());
							p.put(opuesto, minimoV);
						}

					}
				}else {
					System.out.println("el vertice es nulo mirar antes de agregar a S");
				}

			}






		} catch (InvalidKeyException | InvalidVertexException | InvalidEdgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    public static <V> Queue<Vertex<V>> recuperarCamino(Map<Vertex<V>,Vertex<V>> caminos,Vertex<V> destino,Vertex<V> origen)throws InvalidKeyException{
    	Queue<Vertex<V>> cola = new LinkedQueue<Vertex<V>>();
    	
    	recuperarCaminoRecu(caminos,destino,cola);
    	
    	return cola;
    }
    
    private static <V> void recuperarCaminoRecu(Map<Vertex<V>,Vertex<V>> caminos,Vertex<V> destino,Queue<Vertex<V>> cola) throws InvalidKeyException{
    	Vertex<V> anterior = destino;
    	//pregunto si es la fuente
    	if(anterior!=null) {
    		recuperarCaminoRecu(caminos, caminos.get(destino), cola);
    		cola.enqueue(anterior);
    	}
    	
    }
   







}
