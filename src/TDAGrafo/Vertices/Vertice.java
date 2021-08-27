package TDAGrafo.Vertices;

import TDALista.Position;
import TDAMap.OpenHashMap;

public class Vertice<V> extends OpenHashMap<Object,Object> implements Vertex<V> {
    protected Position<Vertice<V>> pos;
    protected V rotulo;
    
    
	public Vertice(Position<Vertice<V>> pos, V rotulo) {
		this.pos = pos;
		this.rotulo = rotulo;
	}

    
	public Vertice(V rotulo) {
		this(null, rotulo);
		
	}
	public Position<Vertice<V>> getPos() {
		return pos;
	}


	public void setPos(Position<Vertice<V>> pos) {
		this.pos = pos;
	}


	public V getRotulo() {
		return rotulo;
	}


	public void setRotulo(V rotulo) {
		this.rotulo = rotulo;
	}


	@Override
	public V element() {
		// TODO Auto-generated method stub
		return this.rotulo;
	}

}
