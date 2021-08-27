package TDADictionary;

import java.util.Iterator;

import TDALista.DoubleLinkedList;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class DiccionarioConLista<K, V> implements Dictionary<K, V> {
    protected PositionList<Entry<K,V>> diccionario;
    
    public DiccionarioConLista() {
        diccionario = new DoubleLinkedList<Entry<K,V>>();
        
    }
	 
	@Override
	public int size() {
		return this.diccionario.size();
	}

	@Override
	public boolean isEmpty() {
		return this.diccionario.isEmpty();
	}

	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);
		boolean esta = false;
		Iterator<Entry<K,V>> iterador = this.diccionario.iterator();
		Entry<K,V> entrada=null;
		while(!esta&&iterador.hasNext()) {
			entrada = iterador.next();
			esta = entrada.getKey().equals(key);
		}
		
		return entrada;
	}

	private void checkKey(K key) throws InvalidKeyException{
		if(key==null)
			throw new InvalidKeyException("key invalido");
		
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		PositionList<Entry<K,V>> listaARetornar = new DoubleLinkedList<Entry<K,V>>();
		
		for(Entry<K,V> entrada : this.diccionario) {
			if(entrada.getKey().equals(key)) {
				listaARetornar.addLast(entrada);
			}
		}
		return listaARetornar;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		Entrada<K,V> entrada = new Entrada<K,V>(key,value);
		this.diccionario.addLast(entrada);
		
	return entrada;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		
		
		if(e==null) {
			throw new InvalidEntryException("la entrada esta nula");
		}
		boolean esta = false;
		Iterable<Position<Entry<K,V>>> posIterable = this.diccionario.positions();
		Iterator<Position<Entry<K,V>>> iterador = posIterable.iterator();
		Position<Entry<K,V>> entrada=null;
		
		
		while(!esta&&iterador.hasNext()) {
			entrada = iterador.next();
			
			if(entrada.element()==e) {
			try {
				this.diccionario.remove(entrada);
				esta=true;
			}catch(InvalidPositionException e1){
				throw new InvalidEntryException("entrada invalida");
			};
			}
			
		}
		if(!esta) {
			throw new InvalidEntryException("la entrada esta nula");
		}
	
		return entrada.element();
				
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		
		return this.diccionario;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
