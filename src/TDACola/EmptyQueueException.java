package TDACola;
/**
 * 
 * @author Sergio J. Antozzi
 *
 */

@SuppressWarnings("serial")
public class EmptyQueueException extends Exception {
/**
* Constructor de la excepción.
* @param msg , mesaje que se envia a la clase Excetion mediante super().
*/
 public EmptyQueueException(String msg) {
 super(msg);
}
}
