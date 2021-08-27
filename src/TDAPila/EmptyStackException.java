package TDAPila;
/**
 *Excepcion para acusar que la pila esta vacia. 
 * @author Sergio J. Antozzi
 *
 */
@SuppressWarnings("serial")
public class EmptyStackException extends Exception{
/**
 * Constructor de la excepción.
 * @param msg , mesaje que se envia a la clase Excetion mediante super().
 */
public EmptyStackException (String msg) {
   super(msg);	
}
}
