package TDAGrafo.Implementaciones.GraphD;


import TDAGrafo.GraphD;
import TDAGrafo.Arcos.Arco;
import TDAGrafo.Arcos.Edge;
import TDAGrafo.Excepciones.InvalidVertexException;
import TDAGrafo.Implementaciones.Graph.ListaDeArcosGraph;
import TDAGrafo.Vertices.Vertex;
import TDAGrafo.Vertices.Vertice;
import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class ListaDeArcosGraphD<V, E> extends ListaDeArcosGraph<V,E> implements GraphD<V, E> {

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		Vertice<V> nodo = checkVertex(v);
		PositionList<Edge<E>> incidentes = new DoubleLinkedList<Edge<E>>();
		
		for(Arco<V,E> arco : arcos) {
			if(arco.getV2()==nodo ) {
				incidentes.addLast(arco);
			}
		}
		return incidentes;
	}

	@Override
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException {
		Vertice<V> nodo = checkVertex(v);
		PositionList<Edge<E>> incidentes = new DoubleLinkedList<Edge<E>>();
		
		for(Arco<V,E> arco : arcos) {
			if( arco.getV1()==nodo) {
				incidentes.addLast(arco);
			}
		}
		return incidentes;
	}
	

}
