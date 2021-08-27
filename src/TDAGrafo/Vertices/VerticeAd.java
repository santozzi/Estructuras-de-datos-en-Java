package TDAGrafo.Vertices;

import TDAGrafo.Arcos.ArcoAd;
import TDALista.DoubleLinkedList;
import TDALista.Position;
import TDALista.PositionList;
import TDAMap.OpenHashMap;

public class VerticeAd<V, E>  extends OpenHashMap<Object,Object> implements Vertex<V>{
    protected Position<VerticeAd<V,E>> posicionEnLaLista;
    
    protected PositionList<ArcoAd<V,E>> listaAdyacencia;
    
	protected V rotulo;
    
	
	
	public VerticeAd(Position<VerticeAd<V, E>> posicionEnLaLista, V rotulo) {
	
		this.posicionEnLaLista = posicionEnLaLista;
		this.rotulo = rotulo;
		this.listaAdyacencia= new DoubleLinkedList<ArcoAd<V,E>>();
	}
   public VerticeAd(V rotulo) {
	   this(null,rotulo);
   }


	@Override
	public V element() {
		return this.rotulo;
	}
	public Position<VerticeAd<V, E>> getPosicionEnLaLista() {
		return posicionEnLaLista;
	}
	public void setPosicionEnLaLista(Position<VerticeAd<V, E>> posicionEnLaLista) {
		this.posicionEnLaLista = posicionEnLaLista;
	}
	public PositionList<ArcoAd<V, E>> getListaAdyacencia() {
		return listaAdyacencia;
	}
	public void setListaAdyacencia(PositionList<ArcoAd<V, E>> listaAdyacencia) {
		this.listaAdyacencia = listaAdyacencia;
	}
	public V getRotulo() {
		return rotulo;
	}
	public void setRotulo(V rotulo) {
		this.rotulo = rotulo;
	}
	

}
