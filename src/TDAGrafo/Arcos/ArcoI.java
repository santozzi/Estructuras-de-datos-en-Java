package TDAGrafo.Arcos;

import TDAGrafo.Vertices.VerticeI;
import TDALista.Position;

public class ArcoI<V,E>implements Edge<E> {
   
	protected Position<ArcoI<V,E>> pos;
	protected VerticeI<V> v1, v2;
	
    public ArcoI(Position<ArcoI<V, E>> pos, VerticeI<V> v1, VerticeI<V> v2, E rotulo) {

    	this.pos = pos;
		this.v1 = v1;
		this.v2 = v2;
		this.rotulo = rotulo;
	}
    public ArcoI( VerticeI<V> v1, VerticeI<V> v2, E rotulo) {
		this(null,v1,v2,rotulo);
	
	}
    
    


	public Position<ArcoI<V, E>> getPos() {
		return pos;
	}
	public void setPos(Position<ArcoI<V, E>> pos) {
		this.pos = pos;
	}
	public VerticeI<V> getV1() {
		return v1;
	}
	public void setV1(VerticeI<V> v1) {
		this.v1 = v1;
	}
	public VerticeI<V> getV2() {
		return v2;
	}
	public void setV2(VerticeI<V> v2) {
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
		return this.rotulo;
	}

}
