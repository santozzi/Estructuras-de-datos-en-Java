package TDACola;
/**
 * 
 * @author Sergio J.Antozzi.
 *
 * @param <E> tipo genérico
 */
public class Nodo<E> {
private E element;
private Nodo<E> siguiente;
/**
 * Constructor de dos parametros.
 * @param elemento, elememento de tiepo genérico E.
 * @param sig Nodo enlazado(siguiente);
 */
public Nodo(E elemento,Nodo<E> sig) {
 element = elemento;
 siguiente = sig;
 
}
/**
 * Constructor de un parámetro.
 * @param elemento elememento de tipo genérico E
 * Invoca al constructor de dos parametros, con elemento y null. 
 */
public Nodo(E elemento) {
 this(elemento,null);
	
}
/**
 * Constructor sin parámetros.
 *Invoca al constructor de dos parámetros con null, null.
 */
public Nodo() {
 this(null,null);	
}
//comandos.
/**
 * Comando para actualizar el valor del atributo element.
 * @param e parámetro tipo E para reemplazar a element.
 */
public void setElement(E e) {
 element =e;
}
/**
 * Comando para actualizar el valor del atributo siguiente.
 * @param sig parámetro tipo Nodo para reemplazar a siguiente.
 */
public void setSiguiente(Nodo<E> sig) {
siguiente = sig;	
	
}

//consultas.
/**
 * Devuelve el element.
 * @return elemento tipo E.
 */
public E getElement() {
 return element;	
	
}
/**
 * Consulta el atributo siguiente.
 * @return el nodo siguiente.
 */
public Nodo<E> getSiguiente(){
  return siguiente;	
	
}

}
