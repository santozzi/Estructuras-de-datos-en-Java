package TDAGrafo.Arcos;

import TDAGrafo.Vertices.VerticeAd;
import TDALista.Position;

public class ArcoAd<V, E> implements Edge<E> {
	protected Position<ArcoAd<V,E>> posicionEnLista;

	protected VerticeAd<V,E> v1,v2;
	protected E rotulo;
	protected Position<ArcoAd<V,E>> posicionEnListaAdyacenciaV1;
	protected Position<ArcoAd<V,E>> posicionEnListaAdyacenciaV2;

    


	public ArcoAd(Position<ArcoAd<V, E>> posicionEnLista, VerticeAd<V, E> v1, VerticeAd<V, E> v2, E rotulo,
			Position<ArcoAd<V, E>> posicionEnListaAdyacenciaV1, Position<ArcoAd<V, E>> posicionEnListaAdyacenciaV2) {
		this.posicionEnLista = posicionEnLista;
		this.v1 = v1;
		this.v2 = v2;
		this.rotulo = rotulo;
		this.posicionEnListaAdyacenciaV1 = posicionEnListaAdyacenciaV1;
		this.posicionEnListaAdyacenciaV2 = posicionEnListaAdyacenciaV2;
	}
   public ArcoAd(VerticeAd<V,E> v1,VerticeAd<V,E> v2,E rotulo) {
	   this(null,v1,v2,rotulo,null,null);
   }



	public Position<ArcoAd<V, E>> getPosicionEnLista() {
	return posicionEnLista;
}
public void setPosicionEnLista(Position<ArcoAd<V, E>> posicionEnLista) {
	this.posicionEnLista = posicionEnLista;
}
public VerticeAd<V, E> getV1() {
	return v1;
}
public void setV1(VerticeAd<V, E> v1) {
	this.v1 = v1;
}
public VerticeAd<V, E> getV2() {
	return v2;
}
public void setV2(VerticeAd<V, E> v2) {
	this.v2 = v2;
}
public E getRotulo() {
	return rotulo;
}
public void setRotulo(E rotulo) {
	this.rotulo = rotulo;
}
public Position<ArcoAd<V, E>> getPosicionEnListaAdyacenciaV1() {
	return posicionEnListaAdyacenciaV1;
}
public void setPosicionEnListaAdyacenciaV1(Position<ArcoAd<V, E>> posicionEnListaAdyacenciaV1) {
	this.posicionEnListaAdyacenciaV1 = posicionEnListaAdyacenciaV1;
}
public Position<ArcoAd<V, E>> getPosicionEnListaAdyacenciaV2() {
	return posicionEnListaAdyacenciaV2;
}
public void setPosicionEnListaAdyacenciaV2(Position<ArcoAd<V, E>> posicionEnListaAdyacenciaV2) {
	this.posicionEnListaAdyacenciaV2 = posicionEnListaAdyacenciaV2;
}
	@Override
	public E element() {
		return this.rotulo;
	}

}
