package TDACola;

public class ArrayQueue<E> implements Queue<E>  {
    protected final int longitud=5;
	protected E[] cola;
	protected int ini;
	protected int fin;
	@SuppressWarnings("unchecked")
	/**
	 * Constructor de la cola que inicializa el atributo arrglo con una
	 *  cantidad de longitud y ini y fin en 0;
	 */
	public ArrayQueue() {
	 cola = (E[]) new Object[longitud];	
	 ini=0;
	 fin=0;
	}
	@Override
	/**
	 * Devuelve el tama�o de la cola.
	 */
	public int size() {
	 return (cola.length-ini+fin)%cola.length;
	}

	@Override
	/**
	 * Devuelve verdadero si el arreglo esta vacio y
	 * falso si no.
	 */
	public boolean isEmpty() {
	 return ini==fin;
	}

	@Override
	/**
	 * Devuelve el primer elemento que entr� en la cola.
	 */
	public E front() throws EmptyQueueException {
	 if (isEmpty())
		 throw new EmptyQueueException("La cola esta vacia");
	return cola[ini];
	}
/**
  * Agrega un nuevo elemento a la cola.
  *  Si la cola est� llena entonces incrementa su tama�o al doble.
  */
	@Override
    public void enqueue(E element){
	 
    if(size()==cola.length-1) {
	
	    E[] aux = copiar(ini);
	     fin= size();
	     ini=0;
	     cola=aux;
    }
    cola[fin]=element;
    
    fin=(fin+1)%cola.length;
	}
	/**
	 * M�todo auxiliar para copiar el contenido de la cola en una cola
	 * del doble de tama�o.
	 * Devuelve una cola con el doble de tama�o cargada con los elementos de la anterior
	 * @param i el inicio de la cola
	 * @return nuevo tipo E[];
	 */
    @SuppressWarnings("unchecked")
	private E[] copiar(int i) {
    
		E[] nuevo = (E[]) new Object[cola.length*2];
    	for(int j=0;j<cola.length-1;j++) {
    	  nuevo[j]=cola[i];
    	  i=(i+1)%cola.length;
    		
    	}
    return nuevo;
    	
    }
	
    /**
     * Devuelve el primer elemento ingresado y lo elimina de la cola.
     */
    @Override
	public E dequeue() throws EmptyQueueException {
		if(isEmpty())
          throw new EmptyQueueException("La cola esta vacia");
	    E aux = cola[ini];
	    cola[ini]=null;
	    ini= (ini+1)%cola.length;
    return aux;
	}

}
