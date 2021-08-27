package TDAGrafo.Implementaciones.GraphD;


import TDAGrafo.GraphD;
import TDAGrafo.Arcos.ArcoAd;
import TDAGrafo.Arcos.Edge;

import TDAGrafo.Excepciones.InvalidVertexException;
import TDAGrafo.Implementaciones.Graph.ListaDeAdyacenciaGraph;
import TDAGrafo.Vertices.Vertex;
import TDAGrafo.Vertices.VerticeAd;
import TDALista.DoubleLinkedList;

import TDALista.PositionList;

public class ListaDeAdyacenciaGraphD<V, E> extends ListaDeAdyacenciaGraph<V, E> implements GraphD<V, E> {

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		VerticeAd<V,E> nodo = checkVertex(v);
		PositionList<Edge<E>> lista = new DoubleLinkedList<Edge<E>>();
		for(ArcoAd<V,E> arco : nodo.getListaAdyacencia()) {
			lista.addLast(arco);
		}
		return lista;
	}
	@Override
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException {
		VerticeAd<V,E> nodo = checkVertex(v);
		PositionList<Edge<E>> lista = new DoubleLinkedList<Edge<E>>();
		for(ArcoAd<V,E> arco : nodo.getListaAdyacencia()) {
			if(arco.getV1()==nodo) {
				lista.addLast(arco);
			}
		}
		return lista;
	}





}
