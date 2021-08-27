package TDAGrafo.Implementaciones.Graph;

import java.util.Iterator;

import TDAGrafo.Graph;

import TDAGrafo.Arcos.ArcoAd;
import TDAGrafo.Arcos.Edge;
import TDAGrafo.Excepciones.InvalidEdgeException;
import TDAGrafo.Excepciones.InvalidVertexException;
import TDAGrafo.Vertices.Vertex;
import TDAGrafo.Vertices.VerticeAd;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.PositionList;

public class ListaDeAdyacenciaGraph<V, E> implements Graph<V, E> {
	protected PositionList<VerticeAd<V,E>> nodos;
	protected PositionList<ArcoAd<V,E>> arcos;


	public ListaDeAdyacenciaGraph() {
		this.nodos = new DoubleLinkedList<VerticeAd<V,E>>();
		this.arcos = new DoubleLinkedList<ArcoAd<V,E>>();
	}
	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista = new DoubleLinkedList<Vertex<V>>();
		for(Vertex<V>nodo : nodos) {
			lista.addLast(nodo);
		}
		return  lista;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new DoubleLinkedList<Edge<E>>();
		for(Edge<E>arco : arcos) {
			lista.addLast(arco);
		}
		return  lista;
	}
	@Override
	
	
	
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		VerticeAd<V,E> nodo = checkVertex(v);
		PositionList<Edge<E>> lista = new DoubleLinkedList<Edge<E>>();
		for(ArcoAd<V,E> arco : nodo.getListaAdyacencia()) {
			
				lista.addLast(arco);
		
		}

		return lista;
	}
	
	

	
	
	@SuppressWarnings("unchecked")
	protected VerticeAd<V,E> checkVertex(Vertex<V> v)throws InvalidVertexException{
		VerticeAd<V,E> nodo= null;
		if(v==null|| nodos.isEmpty()) {
			throw new InvalidVertexException("vertice invalido");
		}else {
			try {
				nodo = (VerticeAd<V,E>)v;
			}catch(ClassCastException e) {
				throw new InvalidVertexException("error de casteo");
			}
		}
		return nodo;
	}

	@SuppressWarnings("unchecked")
	protected ArcoAd<V,E> checkEdge(Edge<E> e)throws InvalidEdgeException{
		ArcoAd<V,E> arco = null;
		if(e==null || arcos.isEmpty()) {
			throw new InvalidEdgeException("arco invalido");
		}else {
			try {
				arco = (ArcoAd<V,E>)e;
				if(arco.getV1()==null||arco.getV2()==null) {
					throw new InvalidEdgeException("arco invalido");

				}
			}catch(ClassCastException e1) {
				throw new InvalidEdgeException("error de casteo");
			}
		}
		return arco;
	}
/*
	@Override
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException {
		VerticeAd<V,E> nodo = checkVertex(v);
		PositionList<Edge<E>> lista = new DoubleLinkedList<Edge<E>>();
		for(ArcoAd<V,E> arco : nodo.getListaAdyacencia()) {
			if(arco.getV1()==nodo) {
				lista.addLast(arco);
			}
		}

		return lista;
	}
*/
	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		VerticeAd<V,E> nodo = checkVertex(v);
		ArcoAd<V,E> arco = checkEdge(e);
		Vertex<V> aRetornar;
		if(arco.getV1()==nodo) {
			aRetornar=arco.getV2();
		}else if(arco.getV2()==nodo) {
			aRetornar=arco.getV1();
		}else {
			throw new InvalidEdgeException("el arco no contiene al nodo");
		}

		return aRetornar;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		ArcoAd<V,E> arco = checkEdge(e);
		@SuppressWarnings("unchecked")
		Vertex<V>[] aRetornar = (Vertex<V>[]) new Vertex[2];
		aRetornar[0] = arco.getV1();
		aRetornar[1] = arco.getV2();

		return aRetornar;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		boolean sonAdyacentes = false;
		VerticeAd<V,E> v1 = checkVertex(v);
		VerticeAd<V,E> v2 = checkVertex(w);

		Iterator<ArcoAd<V,E>> iAdyacentes = v1.getListaAdyacencia().iterator();
		while(!sonAdyacentes&&iAdyacentes.hasNext()) {
			ArcoAd<V,E> arco = iAdyacentes.next();
			if(arco.getV1()==v2||arco.getV2()==v2) {
				sonAdyacentes=true;
			}
		}

		return sonAdyacentes;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		VerticeAd<V,E> nodo = checkVertex(v);
		V aRetornar = nodo.element();
		nodo.setRotulo(x);

		return aRetornar;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		VerticeAd<V,E> nuevo = new VerticeAd<V,E>(x);
		nodos.addLast(nuevo);

		try {
			nuevo.setPosicionEnLaLista(nodos.last());
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}
		return nuevo;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		VerticeAd<V,E> v1 = checkVertex(v);
		VerticeAd<V,E> v2 = checkVertex(w);
		ArcoAd<V,E> nuevo = new ArcoAd<V,E>(v1,v2,e);
		arcos.addLast(nuevo);
		v1.getListaAdyacencia().addLast(nuevo);
		v2.getListaAdyacencia().addLast(nuevo);
		try {
			nuevo.setPosicionEnLista(arcos.last());
			nuevo.setPosicionEnListaAdyacenciaV1(v1.getListaAdyacencia().last());
			nuevo.setPosicionEnListaAdyacenciaV2(v2.getListaAdyacencia().last());
		} catch (EmptyListException e1) {
			System.out.println(e1.getMessage());
		}

		return nuevo;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		VerticeAd<V,E> v1 = checkVertex(v);
		V aRetornar = v1.getRotulo();
		for(ArcoAd<V,E> arco : v1.getListaAdyacencia()) {
			eliminarArco(arco);
		}
		try {
			nodos.remove(v1.getPosicionEnLaLista());
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}

		v1.setRotulo(null);
		v1.setPosicionEnLaLista(null);
		v1.setListaAdyacencia(null);


		return aRetornar;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		ArcoAd<V,E> arco = checkEdge(e);
		return eliminarArco(arco);
	}

	private E eliminarArco(ArcoAd<V,E> arco) {
		E aRetornar= null;

		try {
			aRetornar = arcos.remove(arco.getPosicionEnLista()).getRotulo();
			arco.setPosicionEnLista(null);
			arco.setRotulo(null);
			arco.getV1().getListaAdyacencia().remove(arco.getPosicionEnListaAdyacenciaV1());
			arco.getV2().getListaAdyacencia().remove(arco.getPosicionEnListaAdyacenciaV2());
			arco.setPosicionEnListaAdyacenciaV1(null);
			arco.setPosicionEnListaAdyacenciaV2(null);
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
		arco.setV1(null);
		arco.setV2(null);
		return aRetornar;  
	}

}
