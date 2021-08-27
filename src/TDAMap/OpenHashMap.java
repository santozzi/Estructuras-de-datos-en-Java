package TDAMap;

import java.util.Iterator;

import TDALista.*;


public class OpenHashMap<K, V> implements Map<K,V>{
	protected PositionList<Entrada<K,V>>[] mapeo; 
	protected int size;
	protected final float FACTOR= 0.9f;
	protected int N;

	public OpenHashMap() {
		this.N = 11;
		this.mapeo= new PositionList[this.N];
		for(int i = 0; i<this.N;i++) {
			this.mapeo[i] = new DoubleLinkedList<Entrada<K,V>>();
		}
		this.size=0;
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
		checkKey(key);
		int indice =  funcionHash(key);
		PositionList<Entrada<K,V>> bucket = this.mapeo[indice];
		V aRetornar = null;
	

		Position<Entrada<K,V>> posEntrada= searchKey(key,bucket);
		
		if(posEntrada!=null) {
			Entrada<K,V> entrada =  posEntrada.element();
			
			aRetornar = entrada.getValue();
		}


		return aRetornar;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		int indice =  funcionHash(key);
		PositionList<Entrada<K,V>> bucket = this.mapeo[indice];
		V aRetornar = null;
	
		Position<Entrada<K,V>> posEntrada= searchKey(key,bucket);


		if(posEntrada!=null) {
			Entrada<K,V> entrada =  posEntrada.element();
			aRetornar = entrada.getValue();
			entrada.setValue(value);
		}else {



			//agrego nueva
			bucket.addLast(new Entrada<K,V>(key,value));
			this.size++;

			//comprobar factor
			if(!isFactor()) {
				reHash();
			}
		}


		return aRetornar;
	}




	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		int indice =  funcionHash(key);
		PositionList<Entrada<K,V>> bucket = this.mapeo[indice];
		V aRetornar = null;

		Position<Entrada<K,V>> entrada =  searchKey(key,bucket);

		if(entrada!=null) {
			aRetornar = entrada.element().getValue();
			try {
				bucket.remove(entrada);
				this.size--;
			}catch(InvalidPositionException e) {
				throw new InvalidKeyException("no se encontro la key");
			};
		}

		return aRetornar;
	}


	@Override
	public Iterable<K> keys() {
		PositionList<K> keys = new DoubleLinkedList<K>();
		for(int i=0 ; i<this.mapeo.length;i++) {
			for(Entrada<K,V> entrada : this.mapeo[i]) {
				keys.addLast(entrada.getKey());
			}
		}
		return keys;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> values = new DoubleLinkedList<V>();
		for(int i=0 ; i<this.mapeo.length;i++) {
			for(Entrada<K,V> entrada : this.mapeo[i]) {
				values.addLast(entrada.getValue());
			}
		}
		return values;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> entradas = new DoubleLinkedList<Entry<K,V>>();
		for(int i=0 ; i<this.mapeo.length;i++) {

			for(Entry<K,V> entrada : this.mapeo[i]) {
				entradas.addLast(entrada);
			}
		}
		return entradas;
	}

	//privados
	private Position<Entrada<K,V>> searchKey(K key, PositionList<Entrada<K,V>> lista) {
		boolean esta= false;
		Position<Entrada<K,V>> entrada = null;

		Iterable<Position<Entrada<K,V>>> posBucket = lista.positions();

		Iterator<Position<Entrada<K,V>>> itBucket = posBucket.iterator();
		
		while(!esta&&itBucket.hasNext()) {
			entrada = itBucket.next();
			esta= (entrada.element().getKey().equals(key));
		}

		if(!esta) {
			entrada=null;
		}
		return entrada;
	}
	private void checkKey(K key)throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("clave invalida");
	}

	private int funcionHash(K key) {
		return key.hashCode()%N;
	}
	private boolean isFactor() {

		return (this.size/this.N)<FACTOR;
	}

	private void reHash() {
		PositionList<Entrada<K,V>>[] nuevoMapeo;
		int nuevoTamanio = proximoPrimo(this.N*2);
		nuevoMapeo = new PositionList[nuevoTamanio];


		for(int i=0;i<nuevoMapeo.length;i++) {
			nuevoMapeo[i]= new DoubleLinkedList<Entrada<K,V>>();
		}
		this.N = nuevoTamanio;
		for(int i=0; i<this.mapeo.length;i++) {
			for(Entrada<K,V> entrada : this.mapeo[i]) {
				int indice = funcionHash(entrada.getKey());
				nuevoMapeo[indice].addLast(entrada);
			}
		}
		this.mapeo = nuevoMapeo;

	}
	private boolean esPrimo(int numero) {
		boolean esPrimo= true;
		for(int i=2 ; i<numero&&esPrimo;i++) {
			esPrimo = numero%i!=0;
		}
		return esPrimo;
	}
	private int proximoPrimo(int numero) {
		boolean esPrimo= false;
       //revisar
		while(!esPrimo(++numero)) {

			esPrimo = esPrimo(numero);
		}
		return numero;

	}
}
