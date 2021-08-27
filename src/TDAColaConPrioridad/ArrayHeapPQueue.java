package TDAColaConPrioridad;

import java.util.Comparator;

import TDALista.EmptyListException;

public class ArrayHeapPQueue<K, V> implements PriorityQueue<K, V> {
	protected Entrada<K,V>[] elems;
	protected Comparator<K> comp;
	protected int size;


	@SuppressWarnings("unchecked")
	public ArrayHeapPQueue(int tam, Comparator<K> comp) {
		this.comp= comp;
		this.size=0;
		elems = (Entrada<K,V>[]) new Entrada[tam];
	}



	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(isEmpty()) {
			throw new EmptyPriorityQueueException("Esta vacio");
		}



		return this.elems[1];
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key==null) {
			throw new InvalidKeyException("Clave invalida");
		}
		//nueva entrada
		Entrada<K,V> nuevaEntrada = new Entrada<K,V>(key,value);
		this.elems[++size] = nuevaEntrada;
		int i= size;
		boolean recorre= true;


		while(recorre&&i>1) {
			Entrada<K,V> actual = this.elems[i];
			Entrada<K,V> padre = this.elems[i/2];
			if(comp.compare(actual.getKey(), padre.getKey())<0) {
				Entrada<K,V> aux = actual;
				this.elems[i] = padre;
				this.elems[i/2] = aux;
				i /=2;
			}else {
				recorre=false;
			}
		}


		return nuevaEntrada;
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException("La estructura esta vacia");
		
		Entrada<K,V> aRetornar = this.elems[1];
		
		//si es raiz
		if(this.size==1) {
			this.elems[1]=null;
			this.size=0;
		
		}else {


			Entrada<K,V> ultimaHoja = this.elems[this.size];
			this.elems[1]= ultimaHoja;
			this.elems[this.size]=null;
			this.size--;
		
			

			boolean sigo = true;
			int i= 1;

			while(sigo) {
				System.out.println("el valor de la i es: "+i);
				int hi = i*2;
				int hd = i*2+1;
				boolean tieneHI = hi<=size();
				boolean tieneHD = hd<=size();
				int menorDeLosHijos;
				//si tiene; hijo izquierdo
				if(tieneHI) {
					//si tiene hijo derecho 
					if(tieneHD) {
						if(comp.compare(this.elems[hi].getKey(),
								this.elems[hd].getKey())<0) {
							menorDeLosHijos= hi;
						}else {
							menorDeLosHijos= hd;
						}

						if(comp.compare(this.elems[menorDeLosHijos].getKey(),
								this.elems[i].getKey())<0) {
							this.swap(menorDeLosHijos, i);
							i=menorDeLosHijos;

						}else {
							sigo=false;
						}



					}else {
System.out.println("size: "+size);
						if(comp.compare(this.elems[hi].getKey(), 
								this.elems[i].getKey())<0) {
							this.swap(hi, i);
							i= hi;

						}else {
							sigo=false;
						}
						
					}
				}else {
					sigo=false;
				}

				//comparo entre hermanos y el hermano ganador  comparo con padre y cambio si es necesario
				//si cambio i /=2;
				//si no tiene derecho
				//compraro hi con padre y swap si es necesario
				//si no tiene hijo izq fin
			}

		}


		//si no es raiz
		System.out.println("estoy aca");
		
		return aRetornar;
	}

	private void swap(int i, int j) {
		Entrada<K,V> aux = this.elems[i];
		this.elems[i]= this.elems[j];
		this.elems[j]= aux;
	}

}
