package TDALista;

import java.util.Iterator;


public class SimpleLinkedList<E> implements PositionList<E> {
    protected Node<E> head; 
    protected int size;
    
    public SimpleLinkedList() {
    	this.head = null;
    	this.size=0;
    }	
	
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size==0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if(isEmpty()) {
			throw new EmptyListException("Lista vacia :first");
		}
		return this.head;
	}
	
	

	@Override
	public Position<E> last() throws EmptyListException {
		if(isEmpty()) {
			throw new EmptyListException("Lista vacia :last");
		}
		Node<E> ultimo = this.head;
		
		while(ultimo.getSiguiente()!=null) {
			ultimo = ultimo.getSiguiente();
			
		}
		return ultimo;
	}
	

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Node<E> nodo = checkPosition(p);
		if(nodo.getSiguiente()==null) {
			throw new BoundaryViolationException("No hay siguiente");
		}
		return nodo.getSiguiente();
	}
	

	private Node<E> checkPosition(Position<E> p) throws InvalidPositionException{
		Node<E> nuevo= null;
		
		if(this.head==null) {
			throw new InvalidPositionException("posicion pertenece a otra lista");
		}else if(p==null) {
			throw new InvalidPositionException("pos nula");
		}else {
			try {
				nuevo = (Node<E>)p;
			}catch(ClassCastException e) {
				throw new InvalidPositionException("Error de casteo");
			}
		}
		
		return nuevo;
	}

	
	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Node<E> nodo = checkPosition(p);
		if(p==this.head) {
			throw new BoundaryViolationException("No hay anterior");
		}
		
		//probar si funciona con p
		Node<E> previo = this.head;
		while(previo.getSiguiente()!=nodo&&previo.getSiguiente()!=null) {
			previo = previo.getSiguiente();
		}
		if(previo.getSiguiente()!=nodo)
			throw new InvalidPositionException("el nodo no pertenece a la lista :prev");
		
		
		return previo;
	}
	
	
	

	@Override
	public void addFirst(E element) {
		Node<E> nuevo = new Node<E>(element,head);
		this.head=nuevo;
		
		this.size++;	
	}
	
	

	@Override
	public void addLast(E element) {
		Node<E> nuevo = new Node<E>(element);
		if(isEmpty()) {
			this.head= nuevo;
		}else {
		   Node<E> ultimo = head;
		   while(ultimo.getSiguiente()!=null) {
			   ultimo = ultimo.getSiguiente();
		   }
		   ultimo.setSiguiente(nuevo);
		}
		this.size++;
		
		
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Node<E> nodo = checkPosition(p);
		Node<E> nuevo = new Node<E>(element,nodo.getSiguiente());
		nodo.setSiguiente(nuevo);
		
		
		this.size++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		
		
		Node<E> nodo = checkPosition(p);
		
		Node<E> nuevo = new Node<E>(element,nodo);
		
		
		if(nodo==this.head) {
		   	this.head=nuevo;
	   
		}else {
		
		 Node<E> anterior = head;
		 
		   while(anterior.getSiguiente()!=nodo&&anterior.getSiguiente()!=null) {
			   anterior = anterior.getSiguiente();
		   }
		   
		   if(anterior.getSiguiente()!=nodo) {
			   throw new InvalidPositionException("la posicion no pertenece a la lista");
		   }
		   
		   anterior.setSiguiente(nuevo);
		   anterior=null;
		   	}
	    this.size++;
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		Node<E> nodo = checkPosition(p);
		
		E aRetornar = nodo.element();
		
		if(nodo==this.head) {
			this.head= this.head.getSiguiente();
			nodo.setSiguiente(null);
			nodo=null;
		}else {
		
		Node<E> anterior = head;
		   while(anterior.getSiguiente()!=nodo&&anterior.getSiguiente()!=null) {
			   anterior = anterior.getSiguiente();
		   }
		   if(anterior.getSiguiente()!=nodo) {
			   throw new InvalidPositionException("la posicion no pertenece a la lista");
		   }
		anterior.setSiguiente(nodo.getSiguiente());
		nodo.setSiguiente(null);
		anterior=null;
		}
		
		
		
		this.size--;
		return aRetornar;
	}
	
	

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		Node<E> nuevo = checkPosition(p);
		E aRetornar =  nuevo.element();
		nuevo.setRotulo(element);
		return aRetornar;
	}
	

	@Override
	public Iterator<E> iterator() {
		
		return new ElementIterator<E>(this);
	}
	

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> listaDePosiciones = new SimpleLinkedList<Position<E>>();
		
	    Node<E> nuevo = head;
	    while(nuevo!=null) {
	    	listaDePosiciones.addLast(nuevo);
	    	nuevo = nuevo.getSiguiente();
	    }
		
		return listaDePosiciones;
	}

	
	
	

}
