package TDAPila;

public class LinkedStack<E> implements Stack<E> {
    protected Nodo<E> head;
	protected int size;
	public LinkedStack() {
		head = null;
		size=0;
	}
	@Override
	/**
	 * Devuelve el tamaño de la pila.
	 */
	public int size() {
	return size;
	}

	@Override
	/** Devuelve verdadero si la pila esta vacia.
	 * @return true, si la pila esta vacia.
	 */
	public boolean isEmpty() {
	 return size==0;
	}

	@Override
	/**
	 * Devuelve el elemento del tope de la pila.
	 */
	public E top() throws EmptyStackException {
	 if(isEmpty())
	   throw new EmptyStackException("La pila esta vacia.");
		return head.getElement() ;
	}

	@Override
	/**
	 * Inserta un nuevo elemento en el tope de la pila.
	 */
	public void push(E element) {
	Nodo<E> aux = new Nodo<E>(element,head);
	head=aux;
	aux=null;
	size++;
	}

	@Override
	/**
	 * Devuelve y elimina el elemento tope de la Pila.
	 */
	public E pop() throws EmptyStackException {
	 if (isEmpty())
		 throw new EmptyStackException("La pila esta vacia, no hay nada para eliminar");
	 E elemento = head.getElement();
	 Nodo<E>aux = head;
	 head = head.getSiguiente();
	 aux.setSiguiente(null);
	 aux.setElement(null);
	 aux=null;
	 size--;
	
	return elemento;
	}
	
 
}
