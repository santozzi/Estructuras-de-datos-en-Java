package TDAARbolBinario;

import TDAArbol.InvalidOperationException;
import TDALista.BoundaryViolationException;
import TDALista.InvalidPositionException;
import TDALista.Position;

/**
 * Interface BinaryTree
 * Exstiende la interfaz GTTree, presentada por Goodrich y Tamassia en su
 * cuarta edición (la interfaz para árbol que no incluye operaciones de modificación).
 * @author Cátedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computación, UNS.
 */

public interface BinaryTree<E> extends GTTree<E>
{
	/**
	 * Devuelve la posición del hijo izquierdo de v.
	 * @param v Posición de un nodo.
	 * @return Posición del hijo izquierdo de v.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si v no tiene hijo izquierdo.
	 */
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve la posición del hijo derecho de v.
	 * @param v Posición de un nodo.
	 * @return Posición del hijo derecho de v.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si v no tiene hijo derecho.
	 */
	public Position<E> right(Position<E> v)throws InvalidPositionException, BoundaryViolationException;

	/**
	 * Testea si v tiene un hijo izquierdo.
	 * @param v Posición de un nodo.
	 * @return Verdadero si v tiene un hijo izquierdo y falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.	
	 */
	public boolean hasLeft(Position<E> v) throws InvalidPositionException;
	
	
	/**
	 * Testea si v tiene un hijo derecho.
	 * @param v Posición de un nodo.
	 * @return Verdadero si v tiene un hijo derecho y falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.	
	 */
	public boolean hasRight(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Crea un nodo con rótulo e como raíz del árbol.
	 * @param E Rótulo que se asignará a la raíz del árbol.
	 * @throws InvalidOperationException si el árbol ya tiene un nodo raíz.
	 */
	public Position<E> createRoot(E r) throws InvalidOperationException;
	
	
	/**
	 * Agrega un nodo con rótulo r como hijo izquierdo de un nodo dado.
	 * @param r Rótulo del nuevo nodo.
	 * @param v Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 * @throws InvalidOperationException si v ya tiene un hijo izquierdo.
	 */
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException;


	/**
	 * Agrega un nodo con rótulo r como hijo derecho de un nodo dado.
	 * @param r Rótulo del nuevo nodo.
	 * @param v Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 * @throws InvalidOperationException si v ya tiene un hijo derecho.
	 */
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, 
										InvalidPositionException;


	/**
	 * Elimina el nodo referenciado por una posición dada. Si el nodo tiene un único hijo, el nodo eliminado será reemplazado por su único hijo.
	 * @param v Posición del nodo a eliminar.
	 * @return el rótulo del nodo eliminado.
     * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 * @throws InvalidOperationException si el nodo a eliminar tiene mas de un hijo.
     */
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException;

	/**
	 * Inserta a los árboles T1 y T2 como subárboles hijos de la hoja v (izquierdo y derecho respectivamente).
	 * @param v Posición de una hoja del árbol.
	 * @param T1 árbol binario a insertar como hijo izquierdo de v.
	 * @param T2 árbol binario a insertar como hijo derecho de v. 
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío, o v no corresponde a una hoja.
	 */
	//Pone a T1 y a T2 como subárboles de la hoja v, izquierdo y derecho respectivamente, si v no era hoja da InvalidPositionException.
	  public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException;
	}

	
