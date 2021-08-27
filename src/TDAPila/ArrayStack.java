package TDAPila;

public class ArrayStack<E> implements Stack<E> {
    protected static final int LONGITUD=10;
	protected E[] pila;
    protected int size;
	@SuppressWarnings("unchecked")
	/**
	 * Constructor de un parametro
	 * @param max tamaño inicial del arreglo.
	 */
	public ArrayStack(int max) {
		pila = (E[])new Object[max];
		size=0;
	}
	/**
	 * Constructor sin parametros
	 * Utiliza el atributo de clase LONGITUD
	 * como tamaño inicial.
	 */
	public ArrayStack() {
		this(LONGITUD);
	}
	/**
	 * Tamaño de la pila inicia en 0;
	 */
    @Override
	public int size() {
		return size;
	}
    /**
     * Devuelve true si la pila esta vacia
     * false caso contrario.
     */
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	/**
	 * Devuelve el elemento tope de la pila acusa
	 * una excepcion en caso de que la pila este vacia.
	 */
	@Override
	public E top() throws EmptyStackException {
		if(isEmpty())
		  throw new EmptyStackException("La pila esta vacia");
		
		return pila[size-1];
	}
	/**
	 * Añade un nuevo elemento al final del arreglo
	 * Si el arreglo esta lleno entonces por medio del metodo copiar
	 * duplica el tamaño del mismo.
	 */
	@Override
	public void push(E element) {
		if(size==pila.length)
			pila= copiar();
		pila[size++]=element;
		
	}
	/**
	 * Devuelve el elemento tope de la pila y luego lo elimina.
	 * acusa una excepcion en caso de estar vacia.
	 */
	@Override
	public E pop() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException("Pila vacia");
		E res = pila[size-1];
		pila[size-1]=null;
		size--;
		return res;
	}
	/**
	 * Metodo para agrandar a la pila
	 * @return un arreglo tipo E[] del doble de tamaño de la pila.
	 */
	@SuppressWarnings("unchecked")
	private E[] copiar() {
	 E[] aux = (E[])new Object[pila.length*2];
     for(int i=0;i<size;i++) {
    	aux[i]=pila[i]; 
     }
     return aux;
    	 
	}
	public void invertir() throws EmptyStackException {
		E aux,aux2;
		for(int i=0;i<size/2;i++) {
			aux= pila[i];
			aux2=pila[size-1-i];
			pila[i]=aux2;
			pila[size-1-i]=aux;
		}
    
		}
	public String toString() {
     String res="";
     for(int i=0;i<size;i++) {
    	 res+=pila[i].toString()+" ";
     }
     return res;
    }
			
	
	
   
}
