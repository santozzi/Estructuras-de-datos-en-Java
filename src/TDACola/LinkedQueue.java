package TDACola;

public class LinkedQueue<E> implements Queue<E> {
   protected Nodo<E> head,tail;
   protected int size;
	public LinkedQueue() {
	   head = new Nodo<E>();
	   tail= new Nodo<E>();
	   size=0;
	}
	/**
	 * Tamaño de la cola inicia en 0.
	 */
	@Override
	public int size() {
	 return size;
	}
    /**
     * Devuelve true si esta vacia false caso contrario.
     */
	@Override
	public boolean isEmpty() {
		return size==0;
	}
    /**
     * Devuelve el primer elemento ingresado en la cola.
     * Acusa una excepcion si la cola esta vacia.
     */
	@Override
	public E front() throws EmptyQueueException {
		if(isEmpty())
		  throw new EmptyQueueException("Cola vacia");
		
		return head.getElement();
	}
    /**
     * Agrega un nuevo elemento a la cola.
     */
	@Override
	public void enqueue(E element) {
		Nodo<E> nuevo = new Nodo<E>(element);
		if(isEmpty()) {
		  head=nuevo;
		  tail=head; //preguntar si esta bien.
		}else {
		  tail.setSiguiente(nuevo);
		  tail=tail.getSiguiente();
		  
		}
		size++;
	}
    /**
     * Devuelve el primer elemento de la cola y luego lo elimina. 
     * Acusa una excepcion si la cola esta vacia.
     */
	@Override
	public E dequeue() throws EmptyQueueException {
		if(isEmpty())
		  throw new EmptyQueueException("Cola vacia");
		E aux = head.getElement();
		head= head.getSiguiente();
		size--;
		return aux;
	}

}
