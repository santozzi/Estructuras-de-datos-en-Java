package TDAArbol;

import java.util.Iterator;

import TDACola.ArrayQueue;
import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class ArbolConLista<E> implements Tree<E> {
	protected TNode<E> root;
	protected int size;

	public ArbolConLista() {
		this.root= null;
		this.size= 0;
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
		for(Position<E> pos : this.positions()) {
			lista.addLast(pos.element());
		}
		return lista.iterator();
		
	}
	
	
	private void recPreorden(TNode<E> r, PositionList<E> l) {
		l.addLast(r.element());
		for(TNode<E> p:r.getHijos())
			recPreorden(p,l);
	}
	

	@Override
	public Iterable<Position<E>> positions() {
		
		PositionList<Position<E>> lista = new DoubleLinkedList<Position<E>>();
	
		if(size>0)
			this.inorder(this.root,lista);
		return lista;
	
	}
	
	/**
	 * Recorrido pre-orden del arbol
	 * @param v
	 * @param l
	 */
	private void pre (TNode<E> v,PositionList<Position<E>> l) {
		l.addLast(v);
		for(TNode<E> nodo : v.getHijos()) {
			pre(nodo,l);
		}

	} 
	
	/**
	 * Recorrido Pos-orden del arbol
	 * @param v
	 * @param l
	 */
	private void pos (TNode<E> v,PositionList<Position<E>> l) {
		l.addLast(v);
		for(TNode<E> nodo : v.getHijos()) {
			pre(nodo,l);
		}

	}
	
	/**
	 * Recorrido In-order
	 * @param padre
	 * @param lista
	 */
	private void  inorder ( TNode<E> padre , PositionList<Position<E>> lista) {
		 PositionList<TNode<E>> hijos = padre.getHijos();
		 try {
		 if (hijos.size()==0) {
			  lista.addLast(padre);
		  }else{ 
			  Position<TNode<E> >hijo =  padre.getHijos().first();
		          if (hijos.size() ==1) {
		        	  inorder(hijo.element(),lista);
		        	  lista.addLast(padre);
		          }
		          //caso un hijo
		          else { // caso |hijos| >= 2
		                     inorder(hijo.element(),lista);
		                     lista.addLast(padre); 
		                     
		                     while (hijo != null){
		                         
									if (hijo == hijos.last())
										  hijo= null;
									  else {
										  hijo = hijos.next(hijo); 
										  inorder(hijo.element(),lista);}
								
		                      }
		           }
		    }
		 } catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BoundaryViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	/**
	 * Recorrido por niveles
	 * @param v
	 * @param l
	 */
	private void porNiveles(TNode<E> v, PositionList<Position<E>> l) {
	    Queue<TNode<E>> cola = new ArrayQueue<TNode<E>>();
	    cola.enqueue(v);
	    while(!cola.isEmpty()) {
	    	  
	    	
	       try {
			TNode<E> primero = cola.dequeue();
			l.addLast(primero);
			for(TNode<E> hijo : primero.getHijos()) {
				cola.enqueue(hijo);
			}
			
		} catch (EmptyQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	}
	
	
	private void recPosorden(TNode<E> r,PositionList<Position<E>> l) {
		
		for(TNode<E> p:r.getHijos()) 
			recPosorden(p,l);
       l.addLast(r);
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNode<E> TNode = checkPosition(v);
		E respuesta = TNode.element();
		TNode.setRotulo(e);
		return respuesta;
	}




	@Override
	public Position<E> root() throws EmptyTreeException {
		if(isEmpty()) {
			throw  new EmptyTreeException("Arbol vacio");
		}

		return this.root;
	}


	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNode<E> nodo =  checkPosition(v);
		if(nodo==root) {
			throw new BoundaryViolationException("La raiz no tiene padre");
		}

		return nodo.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		Position<E> pos = new TNode<E>(null);

		PositionList<Position<E>> posiciones = new DoubleLinkedList<Position<E>>();
		for(TNode<E> tNode : nodo.getHijos()) {
			posiciones.addLast(tNode);
		}

		return posiciones;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);

		return !nodo.getHijos().isEmpty();
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return nodo.getHijos().isEmpty();
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);

		return nodo==this.root;
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if(!isEmpty()) {
			throw new InvalidOperationException("El árbol ya tiene raiz");
		}
		this.root = new TNode<E>(e);
		this.size++;
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		
		TNode<E> nodo = checkPosition(p);
		PositionList<TNode<E>> hijos = nodo.getHijos();
		TNode<E> pHijo = new TNode<E>(e);
		pHijo.setPadre(nodo);
		hijos.addFirst(pHijo);
		this.size++;
		return pHijo;
	
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		
		TNode<E> nodo = checkPosition(p);

		PositionList<TNode<E>> hijos = nodo.getHijos();

		TNode<E> uHijo = new TNode<E>(e);
		uHijo.setPadre(nodo);
		hijos.addLast(uHijo);
		this.size++;
		return uHijo;
	
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		
		TNode<E> nodo = checkPosition(p);
		TNode<E> hijoDerecho = checkPosition(rb);
		PositionList<TNode<E>> hijos = nodo.getHijos();
		Position<TNode<E>> hermano = null; 
		TNode<E> nuevo = new TNode<E>(e);

		try {
			boolean esta = false;
			hermano = hijos.first();

			while(!esta) {

				if(hermano.element()==hijoDerecho) {
					esta = true;
					nuevo.setPadre(nodo);
					hijos.addBefore(hermano,nuevo) ;
					this.size++;
				}else {
					hermano = hijos.next(hermano);
				}



			}


		}catch(EmptyListException | BoundaryViolationException e1){
			throw new InvalidPositionException("hermano derecho no pertenece al arbol");
		}





		return nuevo;

	}



	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(p);
		TNode<E> hijoIzquierdo = checkPosition(lb);
		PositionList<TNode<E>> hijos = nodo.getHijos();

		Position<TNode<E>> aux = null; 
		TNode<E> nuevo = new TNode<E>(e);

		try {

			boolean esta = false;
			aux = hijos.first();

			while(!esta) {

				if(aux.element()==hijoIzquierdo) {
					esta = true;
					nuevo.setPadre(nodo);
					hijos.addAfter(aux,nuevo) ;
					this.size++;
				}else {
					aux = hijos.next(aux);
				}



			}


		}catch(EmptyListException | BoundaryViolationException | InvalidPositionException e1){
			throw new InvalidPositionException("hermano derecho no pertenece al arbol");
		}





		return nuevo;

	}


	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		
		TNode<E> nodo = checkPosition(p);
		if(!isExternal(p)) {
			throw new InvalidPositionException("No es una hoja");
		}

		if(p==root) {
			this.root=null;
		}else {

			TNode<E> padre = nodo.getPadre();
			PositionList<TNode<E>> hijos = padre.getHijos();

			try {
				boolean esta = false;
				Position<TNode<E>> buscarHijo = hijos.first();
				while(!esta) {
					if(buscarHijo.element()==nodo) {
						hijos.remove(buscarHijo);
						esta=true;
					}else {
						buscarHijo = hijos.next(buscarHijo);
					}
				}

			} catch (EmptyListException | BoundaryViolationException | InvalidPositionException e) {
				throw new InvalidPositionException("Posicion invalida");
			}

		}
		this.size--;
		
	}



	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		

		TNode<E> padre = checkPosition(p);
		if(!isInternal(p)) {
			throw new InvalidPositionException("no es un nodo interno");
		}

		if(padre==this.root) {
			if(padre.getHijos().size()==1) {

				try {
					TNode<E> hijo = padre.getHijos().first().element();
					this.root = hijo;
					hijo.setPadre(null);


				} catch (EmptyListException e) {
					throw new InvalidPositionException("posicion invalida");
				}
			}else {
				throw new InvalidPositionException("el nodo es root y no tiene un solo hijo");
			}
		}else {

           TNode<E> abuelo = padre.getPadre();

           PositionList<TNode<E>> hijos = padre.getHijos();

           Position<TNode<E>> buscarPadre = null;
           try {
        	 boolean esta= false;
			 buscarPadre = abuelo.getHijos().first();
			 while(!esta) {
				if(buscarPadre.element()==padre) {

					esta= true;
				}else {
			        buscarPadre = abuelo.getHijos().next(buscarPadre);
				} 
			 };



           } catch (EmptyListException | BoundaryViolationException | InvalidPositionException e) {
        	   throw new InvalidPositionException("posicion invalida");
		}

           for(TNode<E> hijo : hijos) {

        	  abuelo.getHijos().addBefore(buscarPadre,hijo);
        	  hijo.setPadre(abuelo);
           }


           abuelo.getHijos().remove(buscarPadre);
			padre= null;




		}
		this.size--;


		

	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(p);
		if(isInternal(nodo)) {
			this.removeInternalNode(p);
		}else {
			this.removeExternalNode(p);
		}


	}
	//Privadas
	private TNode<E> checkPosition(Position<E> v) throws InvalidPositionException{
		TNode<E> nodo = null;
		if(v==null) {
			throw new InvalidPositionException("Posicion invalida");

		}else if(isEmpty()) {
			throw new InvalidPositionException("No corresponde a este árbol");
		}else {
			try {
				nodo = (TNode<E>)v;
			}catch(ClassCastException e) {

			}
		}

		return nodo;
	}
}
