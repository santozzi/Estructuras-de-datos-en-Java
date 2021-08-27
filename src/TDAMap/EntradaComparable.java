package TDAMap;

public class EntradaComparable<K extends Comparable<K>,V>extends Entrada<K,V>   implements Comparable<EntradaComparable<K,V>> {

	public EntradaComparable(K key, V value) {
		super(key, value);
	}

	@Override
	public int compareTo(EntradaComparable<K, V> o) {
		return this.key.compareTo(o.getKey());
	}



}
