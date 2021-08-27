package TDAGrafo;

import java.util.Iterator;

import TDACola.EmptyQueueException;
import TDACola.LinkedQueue;
import TDACola.Queue;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.PositionList;
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














}
