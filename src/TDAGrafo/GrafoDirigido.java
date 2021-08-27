package TDAGrafo;

import TDAGrafo.Arcos.Edge;
import TDAGrafo.Excepciones.InvalidVertexException;
import TDAGrafo.Implementaciones.Graph.ListaDeArcosGraph;
import TDAGrafo.Vertices.Vertex;

public class GrafoDirigido<V, E> extends ListaDeArcosGraph<V, E> implements GraphD<V, E>{

	
	
	
	
	
	@Override
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException {
		// TODO Auto-generated method stub
		return null;
	}

}
