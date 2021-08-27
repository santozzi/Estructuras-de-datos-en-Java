package TDAMap;

import java.util.Comparator;

import TDAABB.ABB;

public class MapeoABB<K extends Comparable<K>, V> implements Map<K, V> {
    
	protected ABB<EntradaComparable<K,V>> arbol;
	protected int size;
	protected Comparator<K> comp;
	
	public MapeoABB(Comparator<K> comp) {
		this.arbol = new ABB<EntradaComparable<K,V>>();
		this.comp= comp;
	}
	
	
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

}
