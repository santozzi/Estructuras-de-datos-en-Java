package TDADictionary;

public interface Entry<K, V> {
 
	/**
	 * Consulta la clave que esta contenida en la entrada.
	 * @return Clave de la entrada.
	 */
	public K getKey();
	/**
	 * Consulta el valor que esta contenido en la entrada.
	 * @return Valor de la entrada.
	 */
	public V getValue();
}