package TDAArbol;

import TDALista.DoubleLinkedList;
import TDALista.Position;
import TDALista.PositionList;

public class TNode<E> implements Position<E> {
    protected TNode<E> padre;
    protected PositionList<TNode<E>> hijos;
    protected E rotulo;
    
    
    
    
	public TNode(TNode<E> padre, E rotulo ) {
		
		this.padre = padre;
		this.hijos = new DoubleLinkedList<TNode<E>>();
		this.rotulo = rotulo;
	}
	public TNode(E rotulo) {
		this(null,rotulo);
	}


	@Override
	public E element() {
	
		return this.rotulo;
	}


	public TNode<E> getPadre() {
		return padre;
	}


	public void setPadre(TNode<E> padre) {
		this.padre = padre;
	}


	public PositionList<TNode<E>> getHijos() {
		return hijos;
	}


	public void setHijos(PositionList<TNode<E>> hijos) {
		this.hijos = hijos;
	}


	public E getRotulo() {
		return rotulo;
	}


	public void setRotulo(E rotulo) {
		this.rotulo = rotulo;
	}
	

}
