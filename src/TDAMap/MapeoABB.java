package TDAMap;

import java.util.Comparator;

import TDAABB.ABB;
import TDAABB.NodoABB;

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
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size==0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("La clave es invalida");
		NodoABB<EntradaComparable<K, V>> encontrada = arbol.buscar(new EntradaComparable<K,V>(key, null));
		V aRetornar = null;
		if(encontrada.getRotulo()!=null) {
			 aRetornar = encontrada.getRotulo().value;
		}
		
		return aRetornar;
	}
	
	
	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key==null) {
			throw new InvalidKeyException("Clave invalida");
		}
		V aRetornar= null;
		NodoABB<EntradaComparable<K, V>> nodoBuscado = arbol.buscar(new EntradaComparable<K,V>(key,null));
		if(nodoBuscado.getRotulo()!=null) {
			aRetornar = nodoBuscado.getRotulo().getValue();
			nodoBuscado.getRotulo().setValue(value);
		}else {
			arbol.insertar(new EntradaComparable<K, V>(key, value));
			this.size++;
		}
		

		return aRetornar;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if(key == null) {
			throw new InvalidKeyException("Clave invalida en remove");
		}
		V aRetornar = null;
		EntradaComparable<K,V> entrada = arbol.eliminar(new EntradaComparable<K,V>(key, null));
		if(entrada!=null) {
			aRetornar = entrada.getValue();
			this.size--;
		}
		
		return aRetornar;
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
