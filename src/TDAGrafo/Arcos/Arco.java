package TDAGrafo.Arcos;

import TDAGrafo.Vertices.Vertice;
import TDALista.Position;

public class Arco<V,E> implements Edge<E> {
   
	protected Position<Arco<V,E>> pos;
	protected Vertice<V> v1, v2;
	
    public Arco(Position<Arco<V, E>> pos, Vertice<V> v1, Vertice<V> v2, E rotulo) {
		this.pos = pos;
		this.v1 = v1;
		this.v2 = v2;
		this.rotulo = rotulo;
	}
    public Arco( Vertice<V> v1, Vertice<V> v2, E rotulo) {
		this(null,v1,v2,rotulo);
	
	}
    
	public Position<Arco<V, E>> getPos() {
		return pos;
	}
	public void setPos(Position<Arco<V, E>> pos) {
		this.pos = pos;
	}
	public Vertice<V> getV1() {
		return v1;
	}
	public void setV1(Vertice<V> v1) {
		this.v1 = v1;
	}
	public Vertice<V> getV2() {
		return v2;
	}
	public void setV2(Vertice<V> v2) {
		this.v2 = v2;
	}
	public E getRotulo() {
		return rotulo;
	}
	public void setRotulo(E rotulo) {
		this.rotulo = rotulo;
	}



	protected E rotulo;
	@Override
	public E element() {
		// TODO Auto-generated method stub
		return this.rotulo;
	}

}
