package TDAARbolBinario;

import java.util.Iterator;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidOperationException;
import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class ArbolBinarioEnlazado<E> implements BinaryTree<E> {
	protected BTPosition<E> root;
	protected int size;

	public ArbolBinarioEnlazado() {
		this.root= null;
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
	public Iterator<E> iterator() {
		PositionList<E> lista = new DoubleLinkedList<E>();
		for(Position<E> nodo : positions()) {
			lista.addLast(nodo.element());
		}
		return lista.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> lista = new DoubleLinkedList<Position<E>>();
		
		if(size>0)
		    pre(this.root,lista);
		return lista;
	}

	private void pre (Position<E> nodo , PositionList<Position<E>> lista) {
		lista.addLast(nodo);
		try {
			for(Position<E> pos : this.children(nodo)) {
				pre(pos,lista);
			}


		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage()+" en pre()");
		}
	}

	private void posOrder(Position<E>nodo, PositionList<Position<E>> lista) {
	
		try {
			if(hasLeft(nodo)) {
				posOrder(left(nodo),lista);
			}
			if(hasRight(nodo)) {
				posOrder(right(nodo),lista);
			}
			lista.addLast(nodo);
			
		} catch (InvalidPositionException e) {
	         System.out.println(e.getMessage()+" en posOrder()");
		} catch (BoundaryViolationException e) {
			 System.out.println(e.getMessage()+" en posOrder()");
		}
		
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		BTPosition<E> nodo = checkPosition(v);
		E aRetornar =  nodo.element();
		nodo.setElement(e);

		return aRetornar;
	}




	@Override
	public Position<E> root() throws EmptyTreeException {
		if(isEmpty()) {
			throw new EmptyTreeException("El arbol no tiene raiz");
		}
		return this.root;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> nodo = checkPosition(v);
		if(nodo==this.root) {
			
			throw new BoundaryViolationException("La raiz no tiene padre");
		}


		return nodo.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		BTPosition<E> nodo = checkPosition(v);

		PositionList<Position<E>> aRetornar = new DoubleLinkedList<Position<E>>();
		if(nodo.getLeft()!=null)
			aRetornar.addLast(nodo.getLeft());

		if(nodo.getRight()!=null) {
			aRetornar.addLast(nodo.getRight());
		}
		return aRetornar;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		BTPosition<E> nodo = checkPosition(v);
		return nodo.getLeft()!=null || nodo.getRight()!=null;
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		BTPosition<E> nodo = checkPosition(v);
		return nodo.getLeft()==null && nodo.getRight()==null;
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		BTPosition<E> nodo = checkPosition(v);

		return nodo ==this.root;
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> nodo = checkPosition(v);
		if(nodo.getLeft()==null) {
			throw new BoundaryViolationException("No tengo izquierdo");
		}
		return nodo.getLeft();
	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> nodo = checkPosition(v);
		if(nodo.getRight()==null) {
			throw new BoundaryViolationException("No tengo derecho");
		}
		return nodo.getRight();
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTPosition<E> nodo = checkPosition(v);


		return nodo.getLeft()!=null;
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTPosition<E> nodo = checkPosition(v);


		return nodo.getRight()!=null;
	}

	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if(!isEmpty()) {
			throw new InvalidOperationException("El arbol ya tiene raiz");
		}
		this.root = new BTNode<E>(null,r);
		this.size++;
		return this.root;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTPosition<E> nodo = checkPosition(v);
		if(nodo.getLeft()!=null) {
			throw new InvalidOperationException("ya tiene hijo izquierdo");
		}
		
		BTPosition<E> hijoIzquierdo = new BTNode<E>(nodo,r);
		hijoIzquierdo.setParent(nodo);
		
		nodo.setLeft(hijoIzquierdo);
		this.size++;
		return hijoIzquierdo;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTPosition<E> nodo = checkPosition(v);
		if(nodo.getRight()!=null) {
			throw new InvalidOperationException("ya tiene hijo derecho");
		}
		BTPosition<E> hijoDerecho = new BTNode<E>(nodo,r);
		hijoDerecho.setParent(nodo);
		nodo.setRight(hijoDerecho);
		this.size++;
		return hijoDerecho;
	}

	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		BTPosition<E> nodo = checkPosition(v);
		E aRetornar = nodo.element();
		if(nodo.getLeft()!=null && nodo.getRight()!=null) {
			throw new InvalidOperationException("Tiene dos hijos");
		}
		if(nodo==this.root) {
			if(nodo.getLeft()==null&&nodo.getRight()!=null) {
				//si esta el hijo derecho
				nodo.getRight().setParent(null);
				this.root=nodo.getRight();
				
			}else if(nodo.getLeft()!=null&&nodo.getRight()==null) {
				//si esta el hijo izquierdo
				nodo.getLeft().setParent(null);
				this.root=nodo.getLeft();
			}else {
				this.root=null;
			}	
         nodo=null;
		}else {
			BTPosition<E> padre = nodo.getParent();
			BTPosition<E> hijo;
			
			
			if(nodo.getLeft()!=null) {
				hijo= nodo.getLeft();
			}else if(nodo.getRight()!=null) {
				hijo= nodo.getRight();
			}else {
				//no tiene hijos ya que si tuviera los dos explota arriba
				hijo=null;
			}
         
			if(padre.getLeft()==nodo) {
				//el nodo es hijo izquierdo
				padre.setLeft(hijo);

			}else {
				//el nodo es hijo derecho
				padre.setRight(hijo);

			}

			if(hijo!=null) {
				hijo.setParent(padre);
			}


		}

		this.size--;

		return aRetornar;
	}

	@Override
	public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		BTPosition<E> nodo = checkPosition(r);
		if(isInternal(r)) {
			throw new InvalidPositionException("Es un nodo interno");
		}
		BTPosition<E> raizT1=null;
		BTPosition<E> raizT2 =null;
		try {
			if(!T1.isEmpty()) {
				raizT1 = checkPosition(T1.root());
				raizT1.setParent(nodo);

			}

			if(!T2.isEmpty()) {
				raizT2 = checkPosition(T2.root());
				raizT2.setParent(nodo);

			}

		}catch(EmptyTreeException e) {
			throw new InvalidPositionException("error inesperado");
		}
		nodo.setLeft(raizT1);
		nodo.setRight(raizT2);
		this.size +=T1.size()+T2.size();

	}
	//privados
	private BTPosition<E> checkPosition(Position<E> v) throws InvalidPositionException{
		BTPosition<E> nodo = null;
		if(v==null) {
			throw new InvalidPositionException("Posicion invalida");
		}else if(isEmpty()) {
			throw new InvalidPositionException("Posicion invalida");
		}else {
			try {
				nodo = (BTPosition<E>) v;
			}catch(ClassCastException e) {
				throw new InvalidPositionException("Error de casteo");
			}
		}

		return nodo;
	}
}
