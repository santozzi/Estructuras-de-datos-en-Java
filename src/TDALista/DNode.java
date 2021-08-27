package TDALista;

public class DNode<E> implements Position<E> {
	protected DNode<E> anterior;
	protected E rotulo;

	protected DNode<E> siguiente;




	public DNode() {
		this(null,null,null);
	}
	public DNode(DNode<E> anterior, E rotulo, DNode<E> siguiente) {

		this.anterior = anterior;
		this.rotulo = rotulo;
		this.siguiente = siguiente;
	}
	public DNode(E rotulo) {
		this(null, rotulo, null);
	}





	public E getRotulo() {
		return rotulo;
	}





	public void setRotulo(E rotulo) {
		this.rotulo = rotulo;
	}





	public DNode<E> getAnterior() {
		return anterior;
	}





	public void setAnterior(DNode<E> anterior) {
		this.anterior = anterior;
	}





	public DNode<E> getSiguiente() {
		return siguiente;
	}





	public void setSiguiente(DNode<E> siguiente) {
		this.siguiente = siguiente;
	}





	@Override
	public E element() {
		return this.rotulo;
	}

}
