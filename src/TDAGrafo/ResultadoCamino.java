package TDAGrafo;

import TDAGrafo.Vertices.Vertex;
import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class ResultadoCamino<V> {
    protected PositionList<Vertex<V>> camino;
    protected int costo;
	public ResultadoCamino() {
		this.camino = new DoubleLinkedList<Vertex<V>>();
		this.costo = 0;
	}
	public PositionList<Vertex<V>> getCamino() {
		return camino;
	}
	public void setCamino(PositionList<Vertex<V>> camino) {
		this.camino = camino;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	public void copiarLista(PositionList<Vertex<V>>lista ) {
		this.camino = new DoubleLinkedList<Vertex<V>>();		
		
		for(Vertex<V> nodo : lista) {
			camino.addLast(nodo);
		}
	}
    
    
}
