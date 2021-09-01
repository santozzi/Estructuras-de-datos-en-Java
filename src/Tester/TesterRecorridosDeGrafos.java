package Tester;

import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDAGrafo.Graph;
import TDAGrafo.GraphD;
import TDAGrafo.RecorridosDeGrafos;
import TDAGrafo.ResultadoCamino;
import TDAGrafo.Arcos.Edge;
import TDAGrafo.Excepciones.InvalidVertexException;
import TDAGrafo.Implementaciones.Graph.ListaDeArcosGraph;
import TDAGrafo.Implementaciones.GraphD.ListaDeArcosGraphD;
import TDAGrafo.Vertices.Vertex;
import TDALista.DoubleLinkedList;
import TDALista.PositionList;
import TDAMap.InvalidKeyException;
import TDAMap.Map;
import TDAMap.OpenHashMap;

public class TesterRecorridosDeGrafos {

    public static void main (String[] args) {
    	Graph<Integer,Integer> G = new ListaDeArcosGraph<Integer, Integer>();
    	GraphD<Character,Float> GDijkstra = new ListaDeArcosGraphD<Character,Float>();
    	
    	Vertex<Character> a = GDijkstra.insertVertex('a');
    	Vertex<Character> b = GDijkstra.insertVertex('b');
    	Vertex<Character> c = GDijkstra.insertVertex('c');
    	Vertex<Character> d = GDijkstra.insertVertex('d');
    	
    	
    	
    	
    	
    	Vertex<Integer> uno = G.insertVertex(1);
    	Vertex<Integer> dos = G.insertVertex(2);
    	Vertex<Integer> tres = G.insertVertex(3);
    	Vertex<Integer> cuatro = G.insertVertex(4);
    	//Vertex<Integer> cinco = G.insertVertex(5);
    	Vertex<Integer> seis = G.insertVertex(6);
    	//Vertex<Integer> siete = G.insertVertex(7);
    	Vertex<Integer> ocho = G.insertVertex(8);
    	//Vertex<Integer> nueve = G.insertVertex(9);
    	//Vertex<Integer> diez = G.insertVertex(10);
    	
    	try {
    		
    		//arcos de GDijkstra
    		
    		
			GDijkstra.insertEdge(a, c, 11f);
			GDijkstra.insertEdge(a, b, 1f);
			GDijkstra.insertEdge(c, d, 1f);
			GDijkstra.insertEdge(b, d, 38f);
			GDijkstra.insertEdge(b, c, 2f);
    		
			Edge<Integer> a1 = G.insertEdge(uno, dos, 12);
			//Edge<Integer> a10 = G.insertEdge(seis, dos, 1);
			Edge<Integer> a2 = G.insertEdge(dos, tres, 15);
			Edge<Integer> a3 = G.insertEdge(tres, seis, 4);
			Edge<Integer> a4 = G.insertEdge(tres, ocho, 17);
			Edge<Integer> a5 = G.insertEdge(seis, cuatro, 8);
			Edge<Integer> a6 = G.insertEdge(cuatro, ocho, 1);
			
			//Edge<Integer> a7 = G.insertEdge(cinco, siete, 1);
			//Edge<Integer> a8 = G.insertEdge(siete,diez, 1);
			//Edge<Integer> a9 = G.insertEdge(siete, nueve, 1);
		
			
			RecorridosDeGrafos.DFS(G);
			PositionList<Vertex<Integer>> camino = new DoubleLinkedList<Vertex<Integer>>();
			boolean encontre = RecorridosDeGrafos.hallarCamino(G, uno, dos, camino);
			limpiar(G);
			PositionList<Vertex<Integer>> ciclo = RecorridosDeGrafos.hallarCiclo(G, dos);
			limpiar(G);
			PositionList<Vertex<Integer>> caminoBFS = new DoubleLinkedList<Vertex<Integer>>();
			limpiar(G);
			
			ResultadoCamino<Integer> actual = new ResultadoCamino<Integer>();
			ResultadoCamino<Integer> minimo = new ResultadoCamino<Integer>();
			minimo.setCosto(Integer.MAX_VALUE);
			RecorridosDeGrafos.hallarCaminoMinimo(G,uno , ocho, actual, minimo);
			limpiar(G);
			RecorridosDeGrafos.BFS(G, uno, caminoBFS);
			Map<Vertex<Character>,Float> costo = new OpenHashMap<Vertex<Character>, Float>();
			Map<Vertex<Character>,Vertex<Character>> anterior = new OpenHashMap<Vertex<Character>, Vertex<Character>>();
			Map<Vertex<Character>,Boolean> visitado = new OpenHashMap<Vertex<Character>, Boolean>();
			
			
			RecorridosDeGrafos.dijkstra(GDijkstra, a, costo, anterior, visitado);
			Queue<Vertex<Character>>  caminoMinimo = RecorridosDeGrafos.recuperarCamino(anterior, d, a);
			
			/*
			 * 
			 * CAMINO DFS
			 * 
			 * 
			 * */
			
			
			System.out.println("CAMINO DFS");
			System.out.println("===========");
			System.out.println("Encontre camino: "+encontre);
			System.out.print("- ");
			for(Vertex<Integer> nodo : camino) {
				System.out.print(nodo.element()+" - ");
			}
			
			/*
			 * 
			 * TESTEO DE HALLAR CICLO DFS
			 * 
			 * 
			 * */
			
			System.out.println();
			System.out.println("CICLO DFS");
			System.out.println("=========");
			System.out.print("- ");
			for(Vertex<Integer> nodo : 	ciclo) {
				System.out.print(nodo.element()+" - ");
			}
			
			/*
			 * 
			 * CAMINO BFS
			 * 
			 * 
			 * */
			
			System.out.println();
			System.out.println("CAMINO BFS");
			System.out.println("===========");
			System.out.print("- ");
			for(Vertex<Integer> nodo : caminoBFS) {
				System.out.print(nodo.element()+" - ");
			}

			/*
			 * 
			 * CAMINO MINIMO DFS MARCA Y DESMARCA
			 * 
			 * 
			 * */
			System.out.println();
			System.out.println("CAMINO MINIMO DFS MARCA Y DESMARCA");
			System.out.println("===================================");
			System.out.print("- ");
			for(Vertex<Integer> nodo : minimo.getCamino()) {
				System.out.print(nodo.element()+" - ");
			}
			System.out.println();
			System.out.println("Costo del camino minimo: "+minimo.getCosto());
			
			
			/*
			 * 
			 * 
			 * CAMINO MINIMO DE a A d hallado con Dijkstra
			 * 
			 * 
			 */
			System.out.println();
			System.out.println("CAMINO MINIMO DE a A d");
			System.out.println("========================");
			while(!caminoMinimo.isEmpty()) {
				System.out.print("-" + caminoMinimo.dequeue().element());
			}
			System.out.println("-");
		} catch (InvalidVertexException | InvalidKeyException | EmptyQueueException e) {
			System.out.println(e.getMessage());
		}
    	
	}
    
    
    
    private static <V,E> void limpiar(Graph<V,E>G) {
    	for(Vertex<V> nodo : G.vertices()) {
    		try {
				nodo.remove(RecorridosDeGrafos.ESTADO);
			} catch (InvalidKeyException e) {
				System.out.println(e.getMessage());
			}
    	}
    }

}
