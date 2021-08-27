package Tester;

// Librerías JUNIT
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// Estructura a testear
import TDAGrafo.*;
import TDAGrafo.Arcos.Edge;
import TDAGrafo.Excepciones.InvalidEdgeException;
import TDAGrafo.Excepciones.InvalidVertexException;
import TDAGrafo.Implementaciones.Graph.ListaDeAdyacenciaGraph;
import TDAGrafo.Implementaciones.Graph.MatrizDeAdyacenciaGrafo;
import TDAGrafo.Vertices.Vertex;

import java.util.Iterator;

/**
 * Tester desarrollado con JUnit 4 para la estructura Graph (no dirigido).
 * 
 * @author Gonzalo Ezequiel Arró - Departamento de Ciencias e Ingeniería de la
 *         Computación - Universidad Nacional del Sur -
 *         gonzalo.arro@hotmail.com
 * 
 * @version 1.0 Agosto 2017
 *
 */

public class GraphTests {

	// OBJETOS DE PRUEBA

	private Graph<Object, Object> graph;
	private Object o1;

	// INICIALIZACIÓN Y LIMPIEZA DE OBJETOS PARA LAS PRUEBAS

	/**
	 * Setup inicial de los objetos utilizados en los casos de prueba. (llamado
	 * antes de cada test)
	 */
	@Before
	public void setUp() {
		graph = new MatrizDeAdyacenciaGrafo<Object, Object>(); // cambiar esta línea para probar
												// distintas implementaciones
		o1 = new Object();
	}

	/**
	 * Limpieza de los objetos utilizados en los casos de prueba. (llamado
	 * después de cada test)
	 */
	@After
	public void tearDown() {
		graph = null; // esto es importante ya que los objetos pueden
		// persistir durante la ejecución de todos los
		// tests, dejando objetos innecesarios en memoria
		// que ya no se están usando
		o1 = null;
	}

	// TESTS

	/**
	 * Comprueba que el método vertices retorna una colección iterable vacía si
	 * no hay vértices en el grafo.
	 */
	@Test
	public void vertices_emptyGraph_returnsEmptyCollection() {
		Iterable<Vertex<Object>> vertices = graph.vertices();
		Iterator<Vertex<Object>> verticesIterator = vertices.iterator();
		assertFalse("El método vertices no retorna una colección iterable vacía para un grafo sin vértices.",
				verticesIterator.hasNext());
	}

	/**
	 * Comprueba que el método edges retorna una colección iterable vacía si no
	 * hay arcos en el grafo.
	 */
	@Test
	public void edges_emptyGraph_returnsEmptyCollection() {
		Iterable<Edge<Object>> edges = graph.edges();
		Iterator<Edge<Object>> edgesIterator = edges.iterator();
		assertFalse("El método edges no retorna una colección iterable vacía para un grafo sin arcos.",
				edgesIterator.hasNext());
	}

	/**
	 * Comprueba que el método incidentEdges lanza InvalidVertexException con un
	 * vértice nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void incidentEdges_nullVertex_throwsIVE() throws InvalidVertexException {
		graph.incidentEdges(null);
	}

	/**
	 * Comprueba que el método incidentEdges devuelve una colección vacía si se
	 * llama sobre un vértice sin arcos.
	 */
	@Test
	public void incidentEdges_vertexWithoutEdges_returnsEmptyCollection() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Iterable<Edge<Object>> incidentEdges = graph.incidentEdges(v1);
		Iterator<Edge<Object>> edgesIterator = incidentEdges.iterator();
		assertFalse("El método incidentEdges no retorna una colección iterable vacía para un vértice sin arcos.",
				edgesIterator.hasNext());
	}

	/**
	 * Comprueba que el método opposite lanza InvalidVertexException con un
	 * vértice nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void opposite_nullVertex_throwsIVE() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		graph.opposite(null, e);
	}

	/**
	 * Comprueba que el método opposite lanza InvalidEdgeException con un arco
	 * nulo.
	 */
	@Test(expected = InvalidEdgeException.class)
	public void opposite_nullEdge_throwsIEE() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.opposite(v1, null);
	}

	/**
	 * Comprueba que el método opposite lanza InvalidEdgeException si el arco no
	 * incide en el vértice.
	 */
	@Test(expected = InvalidEdgeException.class)
	public void opposite_edgeNotConnected_throwsIEE() throws InvalidEdgeException, InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		Vertex<Object> v3 = graph.insertVertex(new Object());
		graph.opposite(v3, e);
	}

	/**
	 * Comprueba que el método opposite retorna el vértice opuesto de un vértice
	 * insertado como primer parámetro del arco (origen).
	 */
	@Test
	public void opposite_predecessorVertex_returnsOpposite() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		assertEquals("El método opposite no retorna el opuesto de un vértice insertado como primer parámetro del arco.",
				v2, graph.opposite(v1, e));
	}

	/**
	 * Comprueba que el método opposite retorna el vértice opuesto de un vértice
	 * insertado como segundo parámetro del arco (destino).
	 */
	@Test
	public void opposite_successorEdge_returnsOpposite() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		assertEquals("El método opposite no retorna el opuesto de un vértice insertado como primer parámetro del arco.",
				v1, graph.opposite(v2, e));
	}

	/**
	 * Comprueba que el método endvertices lanza InvalidEdgeException con un
	 * arco nulo.
	 */
	@Test(expected = InvalidEdgeException.class)
	public void endvertices_nullEdge_throwsIEE() throws InvalidEdgeException {
		graph.endvertices(null);
	}

	/**
	 * Comprueba que el método endVertices retorna correctamente el arreglo de
	 * vértices para un arco.
	 */
	@Test
	public void endVertices_normalBehavior_returnsEndVertexs() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		Vertex<Object> endVertices[] = graph.endvertices(e);
		assertEquals("El método endVertices retorna un arreglo que no tiene exactamente dos vértices.", 2,
				endVertices.length);
		assertEquals(
				"El primer vértice del arreglo retornado por endVertices no coincide con el primer vértice del arco.",
				v1, endVertices[0]);
		assertEquals(
				"El segundo vértice del arreglo retornado por endVertices no coincide con el segundo vértice del arco.",
				v2, endVertices[1]);
	}

	/**
	 * Comprueba que el método areAdjacent lanza InvalidVertexException cuando
	 * el primer vértice es nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void areAdjacent_firstVertexNull_throwsIVE() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.areAdjacent(null, v1);
	}

	/**
	 * Comprueba que el método areAdjacent lanza InvalidVertexException cuando
	 * el segundo vértice es nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void areAdjacent_secondVertexNull_throwsIVE() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.areAdjacent(v1, null);
	}

	/**
	 * Comprueba que el método areAdjacent lanza InvalidVertexException cuando
	 * ambos vértices son nulos.
	 */
	@Test(expected = InvalidVertexException.class)
	public void areAdjacent_bothVerticesNull_throwsIVE() throws InvalidVertexException {
		graph.areAdjacent(null, null);
	}

	/**
	 * Comprueba que el método areAdjacent retorna true para dos vértices
	 * adyacentes con un único arco entre ellos.
	 */
	@Test
	public void areAdjacent_adjacentsVerticesPredecessorFirst_returnsTrue() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		graph.insertEdge(v1, v2, new Object());
		assertTrue("El método areAdjacent no retorna true para dos vértices con un arco entre ellos.",
				graph.areAdjacent(v1, v2));
	}
	
	/**
	 * Comprueba que el método areAdjacent retorna true para dos vértices
	 * adyacentes con un único arco entre ellos.
	 */
	@Test
	public void areAdjacent_adjacentsVerticesSuccessorFirst_returnsTrue() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		graph.insertEdge(v1, v2, new Object());
		assertTrue("El método areAdjacent no retorna true para dos vértices con un arco entre ellos.",
				graph.areAdjacent(v2, v1));
	}

	/**
	 * Comprueba que el método areAdjacent retorna true para dos vértices
	 * adyacentes con más de un arco entre ellos.
	 */
	@Test
	public void areAdjacent_adjacentsVerticesTwoEdges_returnsTrue() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		graph.insertEdge(v1, v2, new Object());
		graph.insertEdge(v1, v2, new Object());
		assertTrue("El método areAdjacent no retorna true para dos vértices con un arco entre ellos.",
				graph.areAdjacent(v1, v2));
	}

	/**
	 * Comprueba que el método areAdjacent retorna false para dos vértices no
	 * adyacentes.
	 */
	@Test
	public void areAdjacent_notAdjacentsVertexs_returnsFalse() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		assertFalse("El método areAdjacent retorna true para dos vértices no adyacentes.", graph.areAdjacent(v1, v2));
	}

	/**
	 * Comprueba que el método replace lanza InvalidVertexException con un
	 * vértice nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void replace_nullVertex_throwsIVE() throws InvalidVertexException {
		graph.replace(null, new Object());
	}

	/**
	 * Comprueba que el método replace retorna correctamente el elemento previo
	 * en el vértice pasado por parámetro.
	 */
	@Test
	public void replace_validVertex_returnsOldElement() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		assertEquals("El método replace no retorna el elemento previo en el vértice pasado por parámetro.", o1,
				graph.replace(v1, new Object()));

	}

	/**
	 * Comprueba que el método replace setea correctamente el nuevo elemento al
	 * vértice pasado por parámetro.
	 */
	@Test
	public void replace_validVertex_setsNewElement() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.replace(v1, o1);
		assertEquals("El método replace no setea correctamente el nuevo elemento.", o1, v1.element());
	}

	/**
	 * Comprueba que el método insertVertex retorna correctamente el vértice
	 * insertado.
	 */
	@Test
	public void insertVertex_emptyGraph_returnsInsertedVertex() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		assertEquals("El método insertVertex no retorna correctamente el vértice insertado.", o1, v1.element());
	}

	/**
	 * Comprueba que un vértice insertado aparece correctamente en la colección
	 * de vértices del grafo.
	 */
	@Test
	public void insertVertex_emptyGraph_newVertexInGraph() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		Iterable<Vertex<Object>> vertices = graph.vertices();
		Iterator<Vertex<Object>> verticesIterator = vertices.iterator();
		assertTrue("Luego de insertar un vértice, se sigue retornando una colección vacía en el método vertices.",
				verticesIterator.hasNext());
		assertEquals(
				"Luego de insertar un vértice, el elemento del mismo no coincide con aquel presente en la colección retornada por el método vertices.",
				v1, verticesIterator.next());
		assertFalse(
				"Luego de insertar un único vértice, el método vertices retorna una colección con más de un elemento.",
				verticesIterator.hasNext());
	}

	/**
	 * Comprueba que el método insertEdge lanza InvalidVertexException cuando el
	 * primer vértice es nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void insertEdge_firstVertexNull_throwsIVE() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.insertEdge(null, v1, new Object());
	}

	/**
	 * Comprueba que el método insertEdge lanza InvalidVertexException cuando el
	 * segundo vértice es nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void insertEdge_secondVertexNull_throwsIVE() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.insertEdge(v1, null, new Object());
	}

	/**
	 * Comprueba que el método insertEdge lanza InvalidVertexException cuando
	 * ambos vértices son nulos.
	 */
	@Test(expected = InvalidVertexException.class)
	public void insertEdge_bothVerticesNull_throwsIVE() throws InvalidVertexException {
		graph.insertEdge(null, null, new Object());
	}

	/**
	 * Comprueba que el método insertEdge retorna correctamente el arco
	 * insertado.
	 */
	@Test
	public void insertEdge_emptyGraph_returnsInsertedEdge() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		assertEquals("El método insertEdge retorna un arco cuyo elemento no coincide con el pasado por parámetro.", o1,
				e.element());
	}

	/**
	 * Comprueba que un arco insertado aparece correctamente en la colección de
	 * arcos del grafo.
	 */
	@Test
	public void insertEdge_emptyGraph_newEdgeInGraph() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		Iterable<Edge<Object>> edges = graph.edges();
		Iterator<Edge<Object>> edgesIterator = edges.iterator();
		assertTrue("Luego de insertar un arco, se sigue retornando una colección vacía en el método edges.",
				edgesIterator.hasNext());
		assertEquals(
				"Luego de insertar un arco, el elemento del mismo no coincide con aquel presente en la colección retornada por el método edges.",
				e, edgesIterator.next());
		assertFalse("Luego de insertar un único arco, el método edges retorna una colección con más de un elemento.",
				edgesIterator.hasNext());
	}

	/**
	 * Comprueba que luego de añadir un arco el mismo aparece como arco
	 * incidente en el vértice pasado como primer parámetro al arco.
	 */
	@Test
	public void insertEdge_emptyGraph_EdgeInV1AdjacencyList() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		Iterable<Edge<Object>> incidentEdgesV1 = graph.incidentEdges(v1);
		Iterator<Edge<Object>> edgesIteratorV1 = incidentEdgesV1.iterator();
		assertTrue(
				"Luego de insertar un arco a partir de un dado vértice, se sigue retornando una colección vacía en los arcos incidentes de dicho vértice.",
				edgesIteratorV1.hasNext());
		assertEquals(
				"Luego de insertar un arco a partir de un dado vértice, el elemento del arco insertado no coincide con aquel devuelto en la lista de arcos incidentes de dicho vértice.",
				e, edgesIteratorV1.next());
		assertFalse(
				"Luego de insertar un arco a partir de un dado vértice, se retorna una colección con más de un elemento en los arcos incidentes de dicho vértice.",
				edgesIteratorV1.hasNext());
	}

	/**
	 * Comprueba que luego de añadir un arco el mismo aparece como arco
	 * incidente en el vértice pasado como segundo parámetro al arco.
	 */
	@Test
	public void insertEdge_emptyGraph_EdgeInV2AdjacencyList() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		Iterable<Edge<Object>> incidentEdgesV2 = graph.incidentEdges(v2);
		Iterator<Edge<Object>> edgesIteratorV2 = incidentEdgesV2.iterator();
		assertTrue(
				"Luego de insertar un arco a partir de un dado vértice, se sigue retornando una colección vacía en los arcos incidentes de dicho vértice.",
				edgesIteratorV2.hasNext());
		assertEquals(
				"Luego de insertar un arco a partir de un dado vértice, el elemento del arco insertado no coincide con aquel devuelto en la lista de arcos incidentes de dicho vértice.",
				e, edgesIteratorV2.next());
		assertFalse(
				"Luego de insertar un arco a partir de un dado vértice, se retorna una colección con más de un elemento en los arcos incidentes de dicho vértice.",
				edgesIteratorV2.hasNext());
	}

	/**
	 * Comprueba que el método removeVertex lanza InvalidVertexException con un
	 * vértice nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void removeVertex_nullVertex_throwsIVE() throws InvalidVertexException {
		graph.removeVertex(null);
	}

	/**
	 * Comprueba que el método removeVertex retorna correctamente el elemento
	 * del vértice eliminado.
	 */
	@Test
	public void removeVertex_validVertex_returnsElement() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		Object element = graph.removeVertex(v1);
		assertEquals("El método removeVertex no retorna el elemento del vértice eliminado.", o1, element);
	}

	/**
	 * Comprueba que luego de eliminar un vértice mediante el método
	 * removeVertex, el mismo ya no es devuelto en la lista de vértices del
	 * grafo.
	 */
	@Test
	public void removeVertex_validVertex_removedFromVerticesList() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		graph.removeVertex(v1);
		Iterable<Vertex<Object>> vertices = graph.vertices();
		Iterator<Vertex<Object>> verticesIterator = vertices.iterator();
		assertFalse(
				"El método vertices no retorna una colección iterable vacía luego de eliminar el único vértice del grafo.",
				verticesIterator.hasNext());
	}

	/**
	 * Comprueba que luego de eliminar un vértice con un arco mediante el método
	 * removeVertex, dicho arco ya no está presente en la lista de arcos del
	 * grafo.
	 */
	@Test
	public void removeVertex_vertexWithEdge_removedEdge() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		graph.insertEdge(v1, v2, new Object());
		graph.removeVertex(v1);
		Iterable<Edge<Object>> edges = graph.edges();
		Iterator<Edge<Object>> edgesIterator = edges.iterator();
		assertFalse(
				"El método edges no retorna una colección iterable vacía luego de eliminar el vértice que tenía el único arco del grafo.",
				edgesIterator.hasNext());
	}

	/**
	 * Comprueba que el método removeEdge lanza InvalidEdgeException con un arco
	 * nulo.
	 */
	@Test(expected = InvalidEdgeException.class)
	public void removeEdge_nullEdge_throwsIEE() throws InvalidEdgeException {
		graph.removeEdge(null);
	}

	/**
	 * Comprueba que el método removeEdge retorna correctamente el elemento del
	 * arco eliminado.
	 */
	@Test
	public void removeEdge_validEdge_returnsElement() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		Object element = graph.removeEdge(e);
		assertEquals("El método removeEdge no retorna el elemento del arco eliminado.", o1, element);
	}

	/**
	 * Comprueba que luego de eliminar un arco mediante el método removeEdge, el
	 * mismo ya no es devuelto en la lista de arcos del grafo.
	 */
	@Test
	public void removeEdge_validEdge_removedFromEdgesList() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		graph.removeEdge(e);
		Iterable<Edge<Object>> edges = graph.edges();
		Iterator<Edge<Object>> edgesIterator = edges.iterator();
		assertFalse(
				"El método edges no retorna una colección iterable vacía luego de eliminar el único arco del grafo mediante removeEdge.",
				edgesIterator.hasNext());
	}
	
	/**
	 * Comprueba que luego de eliminar un arco mediante el método removeEdge, el mismo ya no es devuelvo en la lista de arcos incidentes del predecesor.
	 */
	@Test
	public void removeEdge_validEdge_removedFromPredecessorIncidentEdges() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		graph.removeEdge(e);
		Iterable<Edge<Object>> edgesV1 = graph.incidentEdges(v1);
		Iterator<Edge<Object>> edgesIteratorV1 = edgesV1.iterator();
		assertFalse("Luego de eliminar un arco, el mismo sigue apareciendo en la lista de incidentes del vértice predecesor.", edgesIteratorV1.hasNext());
	}
	
	/**
	 * Comprueba que luego de eliminar un arco mediante el método removeEdge, el mismo ya no es devuelvo en la lista de arcos incidentes del sucesor.
	 */
	@Test
	public void removeEdge_validEdge_removedFromSuccessorIncidentEdges() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		graph.removeEdge(e);
		Iterable<Edge<Object>> edgesV2 = graph.incidentEdges(v2);
		Iterator<Edge<Object>> edgesIteratorV2 = edgesV2.iterator();
		assertFalse("Luego de eliminar un arco, el mismo sigue apareciendo en la lista de incidentes del vértice sucesor.", edgesIteratorV2.hasNext());
	}
	
	/**
	 * Hace una simulación de uso general sobre la estructura.
	 */
	@Test
	public void simulation() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		for (int i = 0; i < 100; i++) {
			graph.insertVertex(new Object());
		}
		Vertex<Object> v3 = graph.insertVertex(new Object());
		Vertex<Object> v4 = graph.insertVertex(new Object());
		
		Iterable<Vertex<Object>> vertices = graph.vertices();
		Iterator<Vertex<Object>> iteradorVertices = vertices.iterator();
		int contadorVertices = 0;
		while (iteradorVertices.hasNext()) {
			iteradorVertices.next();
			contadorVertices++;
		}
		assertEquals("Luego de insertar 104 vértices, la lista de vértices no coincide en tamaño", 104, contadorVertices);
		
		Edge<Object> e1 = graph.insertEdge(v1, v2, new Object());
		Edge<Object> e2 = graph.insertEdge(v1, v3, new Object());
		Edge<Object> e3 = graph.insertEdge(v3, v4, new Object());
		
		assertTrue("areAdjacent retorna false para dos vértices adyacentes.", graph.areAdjacent(v1, v2));
		assertTrue("areAdjacent retorna false para dos vértices adyacentes.", graph.areAdjacent(v1, v3));
		assertTrue("areAdjacent retorna false para dos vértices adyacentes.", graph.areAdjacent(v3, v4));
		assertFalse("areAdjacent retorna true para dos vértices no adyacentes.", graph.areAdjacent(v1, v4));
		assertFalse("areAdjacent retorna true para dos vértices no adyacentes.", graph.areAdjacent(v2, v3));
		assertFalse("areAdjacent retorna true para dos vértices no adyacentes.", graph.areAdjacent(v2, v4));
		
		assertEquals("opposite no retorna el vértice opuesto correctamente.", v3, graph.opposite(v1, e2));
		assertEquals("opposite no retorna el vértice opuesto correctamente.", v1, graph.opposite(v3, e2));
		assertEquals("opposite no retorna el vértice opuesto correctamente.", v1, graph.opposite(v2, e1));
		assertEquals("opposite no retorna el vértice opuesto correctamente.", v2, graph.opposite(v1, e1));
		assertEquals("opposite no retorna el vértice opuesto correctamente.", v4, graph.opposite(v3, e3));
		assertEquals("opposite no retorna el vértice opuesto correctamente.", v3, graph.opposite(v4, e3));
		
		Iterable<Edge<Object>> arcos = graph.edges();
		Iterator<Edge<Object>> iteradorArcos = arcos.iterator();
		int contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("Luego de insertar 3 arcos, la lista de arcos no coincide en tamaño", 3, contadorArcos);
		
		arcos = graph.incidentEdges(v1);
		iteradorArcos = arcos.iterator();
		contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("El método incidentEdges no retorna una colección de tamaño 2 para un vértice con dos arcos.", 2, contadorArcos);
		
		arcos = graph.incidentEdges(v3);
		iteradorArcos = arcos.iterator();
		contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("El método incidentEdges no retorna una colección de tamaño 2 para un vértice con dos arcos.", 2, contadorArcos);
		
		graph.removeEdge(e1);
		graph.removeEdge(e2);
		assertFalse("areAdjacent retorna true para dos vértices no adyacentes.", graph.areAdjacent(v1, v2));
		assertFalse("areAdjacent retorna true para dos vértices no adyacentes.", graph.areAdjacent(v1, v3));
		arcos = graph.edges();
		iteradorArcos = arcos.iterator();
		contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("Luego de insertar 3 arcos y eliminar 2, la lista de arcos no coincide en tamaño", 1, contadorArcos);
		
		
		arcos = graph.incidentEdges(v3);
		iteradorArcos = arcos.iterator();
		contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("El método incidentEdges no retorna una colección de tamaño 1 para un vértice con un solo arcos.", 1, contadorArcos);
		
	}

}
