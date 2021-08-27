package TDALista;

import java.util.Iterator;

import TDAGrafo.Vertices.Vertex;

public class DoubleLinkedList<E> implements PositionList<E> {
    protected int size;
    protected DNode<E> header;
    protected DNode<E> trailer;
    
    public DoubleLinkedList() {
    	this.header = new DNode<E>();
    	this.trailer= new DNode<E>();
    	
    	this.header.setSiguiente(trailer);
    	this.trailer.setAnterior(header);
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
			throw new EmptyListException("la lista esta vacia");
		}
		return this.header.getSiguiente();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(isEmpty()) {
			throw new EmptyListException("la lista esta vacia");
		}
		
		return this.trailer.getAnterior();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> nodo = checkPosition(p);
		if(nodo.getSiguiente()==this.trailer) {
			throw new BoundaryViolationException("no hay siguiente");
		}
		return  nodo.getSiguiente();
	}

	private DNode<E> checkPosition(Position<E> p)throws InvalidPositionException {
		DNode<E> aRetornar =null;
		if(p==null) {
			throw new InvalidPositionException("la posicion es nula");
		}else if(isEmpty()) {
			throw new InvalidPositionException("la lista esta vacia");
		}else {
			try {
				aRetornar = (DNode<E>)p;
			}catch(ClassCastException e) {
				throw new InvalidPositionException("Error de casteo");
			}
		}
		return aRetornar;
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> nodo = checkPosition(p);
		
		if(nodo.getAnterior()==this.header) {
			throw new BoundaryViolationException("no hay anterior");
		}
		return nodo.getAnterior();
	}

	@Override
	public void addFirst(E element) {
		DNode<E> nuevo= new DNode<E>(this.header,element,this.header.getSiguiente());
		this.header.getSiguiente().setAnterior(nuevo);
		this.header.setSiguiente(nuevo);
		this.size++;
		
	}

	@Override
	public void addLast(E element) {
		DNode<E> nuevo = new DNode<E>(this.trailer.getAnterior(),element,this.trailer);
		this.trailer.getAnterior().setSiguiente(nuevo);
		this.trailer.setAnterior(nuevo);
		this.size++;
		
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
        DNode<E> nodo = checkPosition(p); 
		DNode<E> nuevo = new DNode<E>(nodo,element,nodo.getSiguiente());
		nodo.getSiguiente().setAnterior(nuevo);
		nodo.setSiguiente(nuevo);
		this.size++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		 DNode<E> nodo = checkPosition(p);
		 DNode<E> nuevo = new DNode<E>(nodo.getAnterior(),element,nodo);
		 nodo.getAnterior().setSiguiente(nuevo);
		 nodo.setAnterior(nuevo);
		 this.size++;
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		 DNode<E> nodo = checkPosition(p);
		
		 nodo.getAnterior().setSiguiente(nodo.getSiguiente());
		 nodo.getSiguiente().setAnterior(nodo.getAnterior());
		 nodo.setAnterior(null);
		 nodo.setSiguiente(null);
		 this.size--;
		return nodo.element();
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> nodo = checkPosition(p);
		E aRetornar = nodo.element();
		nodo.setRotulo(element);
		return aRetornar ;
	}

	@Override
	public Iterator<E> iterator() {
		
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		DNode<E> aux = this.header.getSiguiente();
		PositionList<Position<E>> nuevaLista = new DoubleLinkedList<Position<E>>();
		while(aux!=this.trailer) {
			nuevaLista.addLast(aux);
			aux= aux.getSiguiente();
		}
		
		return nuevaLista;
	}
	
	public String toString() {
		String aRetornar= "";
		DNode<E> recorredor = this.header.getSiguiente();
		DNode<E> aux = this.header.getSiguiente();

		while(aux!=this.trailer) {
			aRetornar +=aux.element();
			aux= aux.getSiguiente();
		}
		
		return aRetornar;
	}
	



}
