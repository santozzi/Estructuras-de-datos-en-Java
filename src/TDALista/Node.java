package TDALista;

public class Node<E> implements Position<E>{
   //simple
	protected E rotulo;
	protected Node<E> siguiente;
	
	
	
	
	
	public Node(E rotulo, Node<E> siguiente) {
		this.rotulo = rotulo;
		this.siguiente = siguiente;
	}

	public Node(E rotulo) {
		this(rotulo,null);
	}

	public E getRotulo() {
		return rotulo;
	}



	public void setRotulo(E rotulo) {
		this.rotulo = rotulo;
	}



	public Node<E> getSiguiente() {
		return siguiente;
	}



	public void setSiguiente(Node<E> siguiente) {
		this.siguiente = siguiente;
	}



	@Override
	public E element() {
		return this.rotulo;
	}
	
}
