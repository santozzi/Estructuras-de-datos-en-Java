package TDAGrafo.Vertices;

import TDAGrafo.DecorablePosition;
import TDALista.Position;
import TDAMap.OpenHashMap;

public class VerticeDecorado<V> extends OpenHashMap<Object, Object> implements DecorablePosition<V> {
    protected Position<Vertex<V>> posicionEnlaLista;
    protected V rotulo;
    protected int indice;
    
    
	public VerticeDecorado(Position<Vertex<V>> posicionEnlaLista, V rotulo, int indice) {
		super();
		this.posicionEnlaLista = posicionEnlaLista;
		this.rotulo = rotulo;
		this.indice = indice;
	}


	public Position<Vertex<V>> getPosicionEnlaLista() {
		return posicionEnlaLista;
	}


	public void setPosicionEnlaLista(Position<Vertex<V>> posicionEnlaLista) {
		this.posicionEnlaLista = posicionEnlaLista;
	}


	public V getRotulo() {
		return rotulo;
	}


	public void setRotulo(V rotulo) {
		this.rotulo = rotulo;
	}


	public int getIndice() {
		return indice;
	}


	public void setIndice(int indice) {
		this.indice = indice;
	}


	@Override
	public V element() {
		return this.rotulo;
	}

}
