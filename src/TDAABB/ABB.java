package TDAABB;

import java.util.Comparator;

import TDAARbolBinario.ArbolBinarioEnlazado;

public class ABB<E extends Comparable<E>> extends ArbolBinarioEnlazado<E> {
	protected NodoABB<E> root;

	protected Comparator<E> comp;

	public ABB(Comparator<E> comp) {
		this.root= new NodoABB<E>(null,null);
		this.comp=comp;
	}

	public ABB() {
		this(null);
		this.comp= new DefaultComparator<E>();
	}


	public NodoABB<E> buscar(E rotulo){
		return buscarAux(rotulo,this.root);
	}
	private NodoABB<E> buscarAux(E rotulo, NodoABB<E> nodo){
		if(nodo.getRotulo()==null) {
			return nodo;
		}else {
			//rotulo es a, nodo.getRotulo() e b, a.compareTo(b)
			int c = this.comp.compare(rotulo, nodo.getRotulo());
			if(c==0) {
				return nodo;
			}else if(c>0) {
				return buscarAux(rotulo, nodo.getRight());
			}else {
				return buscarAux(rotulo, nodo.getLeft());
			}
		}
	}
	


	public E eliminar(E elemento) {
		E aRetornar = null;
		//buscar al nodo con elemento "elemento"
		NodoABB<E> nodoEncontrado = buscar(elemento);
		//si lo encuentra
		if(nodoEncontrado.getRotulo()!=null) {
			//guardar elemento del nodo encontrado
			aRetornar = nodoEncontrado.getRotulo();
			eliminarAux(nodoEncontrado);
		}



		return aRetornar;
	}

	private void eliminarAux(NodoABB<E>nodo) {
		//no tiene hijos

		if(isExternal(nodo)) {
			nodo.setRotulo(null);
			nodo.setLeft(null);
			nodo.setRight(null);
		}else {
			//eliminar es la raiz
			if(nodo==this.root) {

				if(this.soloTieneHijoIzquierdo(nodo)) {
					//tiene solo el hijo izquierdo	
					this.root=nodo.getLeft();
					nodo.getLeft().setParent(null);


				}else if(this.soloTieneHijoDerecho(nodo)) {
					//tiene solo el hijo derecho
					nodo.getRight().setParent(null);
					this.root=nodo.getRight();
				}else {
					//tiene los dos hijos
					nodo.setRotulo(this.eliminarMin(nodo.getRight()));
				}

			}else {	
           
				if(this.soloTieneHijoIzquierdo(nodo)) {
					  //tiene solo el hijo izquierdo
                    //averiguar que hijos es nodo de el padre de nodo
					NodoABB<E> padre = nodo.getParent();
					
					if(padre.getLeft()==nodo) {
						//soy hijo izquierdo
						padre.setLeft(nodo.getLeft());
					}else {
						//soy hijo derecho
						padre.setRight(nodo.getLeft());
					}
					nodo.getLeft().setParent(padre);
					
						
				}else if(this.soloTieneHijoDerecho(nodo)) {
					//tiene solo el hijo derecho
					NodoABB<E> padre = nodo.getParent();
					if(padre.getLeft()==nodo) {
						//soy hijo izquierdo
						padre.setLeft(nodo.getRight());
					}else {
						//soy hijo derecho
						padre.setRight(nodo.getRight());
					}
					nodo.getRight().setParent(padre);
				}else {
					//tiene los dos hijos
					nodo.setRotulo(this.eliminarMin(nodo.getRight()));
					
				}
			}

		}      


	}

	private boolean soloTieneHijoIzquierdo(NodoABB<E> nodo) {
		boolean aRetornar = false;
		if(nodo.getRotulo()!=null) {
			if(nodo.getLeft().getRotulo()!=null&&nodo.getRight().getRotulo()==null) {
				aRetornar=true;
			}
		}


		return aRetornar;
	}
	private boolean soloTieneHijoDerecho(NodoABB<E> nodo) {
		boolean aRetornar = false;
		if(nodo.getRotulo()!=null) {
			if(nodo.getLeft().getRotulo()==null&&nodo.getRight().getRotulo()!=null) {
				aRetornar=true;
			}
		}


		return aRetornar;
	}
	
	
	
	private E eliminarMin(NodoABB<E> nodo) {
		E aRetornar;
		//pregunar si tiene left
		if(nodo.getLeft().getRotulo()==null) {
			aRetornar = nodo.getRotulo();
			//no tiene 
			//si no tiene derecho
			if(nodo.getRight().getRotulo()==null) {
			  	nodo.setRotulo(null);
			  	nodo.setLeft(null);
			  	nodo.setRight(null);
			}else {
				//si tiene derecho
				NodoABB<E> padre = nodo.getParent();
				nodo.getRight().setParent(padre);
				padre.setRight(nodo.getRight());
				
			}
			
		}else {
		  //si tiene
			//volver a llamar con hijo izquierdo
			aRetornar = eliminarMin(nodo.getLeft());
		}
		
		
	
		
		
		
		return aRetornar;
	}
	
	
	public boolean isExternal(NodoABB<E> nodo) {


		return nodo.getRotulo()!=null&&
				nodo.getLeft().getRotulo()==null&&
				nodo.getRight().getRotulo()==null;
	}
  
	/**
	 * Insertar un elemento
	 * @param element El elemento debe ser comparable
	 * @return Devuelve verdadero si lo inserta y falso si el elemento ya existe
	 */
	
	public boolean insertar(E element) {
       
		return insertaAux(element,this.root);
	}
	private boolean insertaAux(E rotulo, NodoABB<E> nodo){
		if(nodo.getRotulo()==null) {
			nodo.setRotulo(rotulo);
			
			//para que muestre por pantalla valores de testing
			if(nodo==this.root)
			System.out.println("soy raiz: "+rotulo);
			else {
				System.out.println("mi padre es: "+nodo.getParent().getRotulo());
				System.out.println("mi rotulo: "+nodo.getRotulo());
			}
			
			
				expandir(nodo);
			return true;

		}else {
			//rotulo es a, nodo.getRotulo() e b, a.compareTo(b)
			int c = this.comp.compare(rotulo, nodo.getRotulo());
			if(c==0) {
				 System.out.println("=");
				return false;
			}else if(c>0) {
				System.out.println("=>");
				return insertaAux(rotulo, nodo.getRight());
			}else {
				System.out.println("<=");
				return insertaAux(rotulo, nodo.getLeft());
			}
		}
	}




	public boolean pertenece(E element) {
		return buscar(element).getRotulo()!=null;
	}
	public void expandir(NodoABB<E> nodo) {
		nodo.setLeft(new NodoABB<E>(nodo,null));
		nodo.setRight(new NodoABB<E>(nodo,null));

	}
	public NodoABB<E> getRoot(){
		return this.root;
	}

}
