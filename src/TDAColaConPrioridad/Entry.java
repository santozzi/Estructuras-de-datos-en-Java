package TDAColaConPrioridad;

public interface Entry<K,V> {
/**
 * Retrona el valor de la Key
 * @return K de tipo generico
 */
public K getKey();
/**
 * Retrona el valor de la prioridad Value
 * @return V de tipo generico
 */
public V getValue();
}
