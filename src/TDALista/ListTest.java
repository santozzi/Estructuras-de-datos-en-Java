package TDALista;

import static org.junit.Assert.*;
import java.util.*;

import java.util.Iterator;

import org.junit.*;




public class ListTest {
	private PositionList<String> l; // interface
	private String n1, n2, n3, n4, n5, n6;

	/*
	 * Inicializa la lista antes de cada test individual
	 */

	private PositionList<String> getList() {
		return new DoubleLinkedList();
	}

	@Before
	public void setUp() {
		l = getList();
		n1 = "Uno";
		n2 = "Dos";
		n3 = "Tres";
		n4 = "Cuatro";
		n5 = "Cinco";
		n6 = "Seis";

	}

	/*
	 * _______________________TESTEAMOS EL METODO size()_____________________________*/

	@Test
	public void size() {
		// Caso_de_prueba: Lista Vacía.
		assertTrue("Tamaño de la lista justo después de ser creada != 0",
				l.size() == 0);
		// Caso_de_prueba: Lista con un único elemento.
		l.addLast(n1);
		assertTrue("Tamaño de la lista luego de insertar un elemento != 1",
				l.size() == 1);
		// Caso_de_prueba: Lista con varios elementos.
		l.addLast(n2);
		assertTrue("Tamaño de la lista luego de insertar dos elementos != 2",
				l.size() == 2);
		l.addLast(n3);
		assertTrue("Tamaño de la lista luego de insertar tres elementos != 3",
				l.size() == 3);
		try {
			l.remove(l.first());
			assertTrue(
					"El tamaño de la lista luego de insertar 3 elementos y eliminar uno es != 2",
					l.size() == 2);
			l.remove(l.last());
			assertTrue(
					"El tamaño de la lista luego de insertar 3 elementos y eliminar dos es != 1",
					l.size() == 1);
			l.remove(l.first());
			assertTrue(
					"El tamaño de la lista luego de insertar 3 elementos y eliminar los tres es != 0",
					l.size() == 0);
		} catch (InvalidPositionException e) {
			fail("Al eliminar un elemento de una lista que no está vacía lanza la excepción InvalidPositionException");
		}
		catch (EmptyListException e) {
			fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");
		}
	}

	/*
	 * _______________________TESTEAMOS EL METODO isEmpty()_____________________________*/

	@Test
	public void isEmpty() {
		// Caso_de_prueba: Lista Vacía.
		assertTrue("La lista no está vacía justo después de ser creada",
				l.isEmpty());
		// Caso_de_prueba: Lista con un único elemento.
		l.addFirst(n1);
		assertFalse(
				"La lista está vacía justo después de insertar un elemento",
				l.isEmpty());
		// Caso_de_prueba: Lista con varios elementos.
		for (int i = 0; i < 10; i++) {
			l.addFirst(n1);
			assertFalse(
					"La lista está vacía justo después de insertar elementos",
					l.isEmpty());
		}
		try {
			for (int i = 0; i < 10; i++) {
				l.remove(l.first());
				assertFalse("La lista está vacía teniendo elementos",
						l.isEmpty());
			}
		} catch (InvalidPositionException e) {
			fail("Al eliminar un elemento de una lista con un único elemento lanza la excepción InvalidPositionException");
		}
		catch (EmptyListException e) {
			fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");
		}


		// Caso_de_prueba: Lista Vacía.
		try {
			l.remove(l.first());
			assertTrue(
					"La lista no está vacía luego de eliminar el único elemento que contenía",
					l.isEmpty());
		} catch (InvalidPositionException e) {
			fail("Al eliminar un elemento de una lista con un único elemento lanza la excepción InvalidPositionException");
		}
		catch (EmptyListException e) {
			fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");
		}
	}

	/*
	 * _______________________TESTEAMOS EL METODO first()_____________________________ */

	@Test
	public void first() {
		// Caso_de_prueba: Lista Vacía.
		try{
			l.first();
			fail("Al solicitar la primer posición de una lista vacía debería haber lanzado la excepción EmptyListException");
		}catch (EmptyListException e) {}		
		try{
			// Caso_de_prueba: Lista con un único elemento.
			l.addFirst(n1);
			assertTrue(
					"first() no funciona correctamente para una lista de un elemento.",
					l.first().element() == n1);
			// Caso_de_prueba: Lista con varios elementos.
			l.addFirst(n2);
			assertTrue(
					"first() no funciona correctamente para una lista de dos elemento.",
					l.first().element() == n2);
			l.addLast(n3);
			assertTrue(
					"first() no funciona correctamente para una lista de tres elemento.",
					l.first().element() == n2);
		}catch (EmptyListException e) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		try {
			l.remove(l.first());
			assertTrue(
					"first() no funciona correctamente para una lista de dos elemento.",
					l.first().element() == n1);
			// Caso_de_prueba: Lista con un único elemento.
			l.remove(l.first());
			assertTrue(
					"first() no funciona correctamente para una lista de un elemento.",
					l.first().element() == n3);
		} catch (InvalidPositionException e2) {
			fail("Error_ InvalidPositionException al invocar remove() en el testeo del método first()");
		}catch (EmptyListException e) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
	}

	/*
	 * _______________________TESTEAMOS EL METODO last()_____________________________ */

	@Test
	public void last() {
		// Caso_de_prueba: Lista Vacía.
		try{ l.last();
		fail("Al solicitar la última posición de una lista vacía debería haber lanzado la excepción EmptyListException");
		}catch (EmptyListException e) {}
		try{
			// Caso_de_prueba: Lista con un único elemento.
			l.addFirst(n1);
			assertTrue(
					"last() no funciona correctamente para una lista de un elemento.",
					l.last().element() == n1);
			// Caso_de_prueba: Lista con varios elementos.
			l.addLast(n2);
			assertTrue(
					"last() no funciona correctamente para una lista de dos elemento.",
					l.last().element() == n2);
			l.addFirst(n3);
			assertTrue(
					"last() no funciona correctamente para una lista de tres elemento.",
					l.last().element() == n2);
		}catch (EmptyListException e){ fail("Al solicitar la última posición de una lista que no está vacía se lanza la excepción EmptyListException");}
		try {
			l.remove(l.last());
			assertTrue(
					"last() no funciona correctamente para una lista de dos elemento.",
					l.last().element() == n1);
			// Caso_de_prueba: Lista con un único elemento.
			l.remove(l.last());
			assertTrue(
					"last() no funciona correctamente para una lista de un elemento.",
					l.last().element() == n3);
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar remove() en el testeo del método last()");
		} catch (EmptyListException e) { fail("Al solicitar la última posición de una lista que no está vacía se lanza la excepción EmptyListException");}
	}

	/*
	 * _______________________TESTEAMOS EL METODO next()_____________________________ */

	@Test
	public void next() {
		Position<String> cursor = null;
		// Caso_de_prueba: Lista Vacía.
		try {
			cursor = l.next(cursor);
			fail("Al invocar next() sobre una lista vacía no lanza la excepción InvalidPositionException");
		} catch (InvalidPositionException e) {
		} catch (BoundaryViolationException e2) {
			fail("next() no debería lanzar la excepción BoundaryViolationException si la lista está vacía");
		}
		l.addLast(n1);
		l.addLast(n2);
		l.addLast(n3);
		try {
			cursor = l.first();
			// Caso_de_prueba: Posición Inválida
			cursor = l.next(null);
			fail("Al invocar next() con una posición nula no lanza la excepción InvalidPositionException");
		} catch (InvalidPositionException e) {
		} catch (BoundaryViolationException e2) {
			fail("next() no debería lanzar la excepción BoundaryViolationException con una posición nula");
		} catch (EmptyListException e) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// Caso_de_prueba: Última posición de la lista.
		try {
			cursor = l.next(l.last());
			fail("Al invocar next() con una la última posición de la lista no lanza la excepción BoundaryViolationException");
		} catch (InvalidPositionException e) {
			fail("next() no debería lanzar la excepción InvalidPositionException con la última posición de la lista");
		} catch (BoundaryViolationException e2) {
		} catch (EmptyListException e) {fail("Al solicitar la última posición de una lista que no está vacía lanza la excepción EmptyListException");}

		// Caso_de_prueba: Lista con varios elementos.
		try {
			assertTrue("next() no funciona correctamente.", l.next(cursor)
					.element() == n2);
			cursor = l.next(cursor);
			assertTrue("next() no funciona correctamente.", l.next(cursor)
					.element() == n3);
			cursor = l.next(cursor);
		} catch (InvalidPositionException e) {
			fail("next() no debería lanzar la excepción InvalidPositionException con una posición válida");
		} catch (BoundaryViolationException e2) {
			fail("next() no debería lanzar la excepción BoundaryViolationException con una posición distinta a la última posición de la lista");
		}
		try {
			cursor = l.next(cursor);
			fail("Al invocar next() con una posición distinta a la última posición de la lista no lanza la excepción BoundaryViolationException.");
		} catch (InvalidPositionException e) {
			fail("next() no debería lanzar la excepción InvalidPositionException con una posición válida");
		} catch (BoundaryViolationException e2) {
		}

		try {
			l.remove(l.first());
			l.remove(l.first());
			l.remove(l.first());
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar remove() con una posición válida en el testeo del método next()");
		} catch (EmptyListException e) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		try {	
			for (int i = 0; i < 1000; i++)
				l.addLast(String.valueOf(i));
			cursor = l.first();

			for (int i = 0; i < 999; i++) {
				assertTrue("next() no funciona correctamente.", cursor
						.element().equals(String.valueOf(i)));
				cursor = l.next(cursor);
			}
		} catch (InvalidPositionException e) {
			fail("next() no debería lanzar la excepción InvalidPositionException con una posición válida");
		} catch (BoundaryViolationException e2) {
			fail("next() no debería lanzar la excepción BoundaryViolationException con una posición distinta a la última posición de la lista");
		} catch (EmptyListException e) { fail("Al solicitar la primer posición de la lista no debería lanzar la excepción EmptyListException");}
		try {
			cursor = l.next(cursor);
			fail("next() no debería lanzar la excepción BoundaryViolationException con una posición distinta a la última posición de la lista");
		} catch (InvalidPositionException e) {
			fail("next() no debería lanzar la excepción InvalidPositionException con una posición válida");
		} catch (BoundaryViolationException e2) {
		}
	}

	/*
	 * _______________________TESTEAMOS EL METODO prev()_____________________________ */

	@Test
	public void prev() {
		Position<String> cursor = null;
		int i = 0;
		// Caso_de_prueba: Lista Vacía.
		try {
			cursor = l.prev(cursor);
			fail("Al invocar prev() sobre una lista vacía no lanza la excepción InvalidPositionException");
		} catch (InvalidPositionException e) {
		} catch (BoundaryViolationException e2) {
			fail(" prev() no debería lanzar la excepción BoundaryViolationException si la lista está vacía");
		}
		try {
			l.addLast(n1);
			l.addLast(n2);
			l.addLast(n3);
			cursor = l.last();
			// Caso_de_prueba: Posición Inválida.
			cursor = l.prev(null);
			fail("Al invocar prev() con una posición nula no lanza la excepción InvalidPositionException");
		} catch (InvalidPositionException e) {
		} catch (BoundaryViolationException e2) {
			fail("prev() no debería lanzar la excepción BoundaryViolationException con una posición nula");
		} catch (EmptyListException e) {fail("Al solicitar la última posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// Caso_de_prueba: Primer posición de la lista.
		try {
			cursor = l.prev(l.first());
			fail("Al invocar prev() con una la primer posición de la lista no lanza la excepción BoundaryViolationException");
		} catch (InvalidPositionException e) {
			fail("prev() no debería lanzar la excepción InvalidPositionException con la primer posición de la lista");
		} catch (BoundaryViolationException e2) {
		} catch (EmptyListException e) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}

		// Caso_de_prueba: Lista con varios elementos.
		try {
			assertTrue("prev() no funciona correctamente.", l.prev(cursor)
					.element() == n2);
			cursor = l.prev(cursor);
			assertTrue("prev() no funciona correctamente.", l.prev(cursor)
					.element() == n1);
			cursor = l.prev(cursor);
		} catch (InvalidPositionException e) {
			fail("prev() no debería lanzar la excepción InvalidPositionException con una posición válida");
		} catch (BoundaryViolationException e2) {
			fail("prev() no debería lanzar la excepción BoundaryViolationException con una posición distinta a la primer posición de la lista");
		}
		try {
			cursor = l.prev(cursor);
			fail("Al invocar prev() con la primer posición de la lista debería lanzarse la excepción BoundaryViolationException");
		} catch (InvalidPositionException e) {
			fail("prev() no debería lanzar la excepción InvalidPositionException con una posición válida");
		} catch (BoundaryViolationException e2) {
		}

		try {
			l.remove(l.first());
			l.remove(l.first());
			l.remove(l.first());
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar remove() con una posición válida en el testeo del método prev()");
		} catch (EmptyListException e) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}

		try {
			for (i = 0; i < 1000; i++)
				l.addLast(String.valueOf(i));
			cursor = l.last();
			for (i = 999; i >= 0; i--) {
				assertTrue("prev no funciona correctamente", cursor.element()
						.equals(String.valueOf(i)));
				cursor = l.prev(cursor);
				if (i == 0)
					fail("Al invocar prev() con una la primer posición de la lista no lanza la excepción BoundaryViolationException");
			}
		} catch (InvalidPositionException e) {
			fail("prev() no debería lanzar la excepción InvalidPositionException con una posición válida");
		} catch (BoundaryViolationException e2) {
			if (i != 0)
				fail("prev() no debería lanzar la excepción BoundaryViolationException con una posición distinta a la primer posición de la lista");
		} catch (EmptyListException e2) {fail("Al solicitar la última posición de una lista que no está vacía lanza la excepción EmptyListException");}

	}

	/*
	 * _______________________TESTEAMOS EL METODO addFirst()_________________________ */

	@Test
	public void addFirst() {
		Position<String> cursor = null;
		int i = 0;
		try {
			// Caso_de_prueba: Lista con varios elementos.(Hacia adelante)
			for (i = 0; i < 1000; i++)
				l.addFirst(String.valueOf(i));
			cursor = l.first();
			for (i = 999; i >= 0; i--) {
				assertTrue("addFirst() no funciona correctamente", cursor
						.element().equals(String.valueOf(i)));
				cursor = l.next(cursor);
				if (i == 0)
					fail("Error_ El método next() debería haber lanzado la excepción BoundaryViolationException al invocarlo con la última posición de la lista en el testeo del método addFirst()");
			}
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar next() con una posición válida en el testeo del método addFirst()");
		} catch (BoundaryViolationException e2) {
			if (i != 0)
				fail("Error_ BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista en el testeo del método addFirst()");
		} catch (EmptyListException e2) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// Caso_de_prueba: Lista con varios elementos.(Hacia atras)
		try {
			cursor = l.last();
			for (i = 0; i <= 999; i++) {
				assertTrue("addFirst() no funciona correctamente", cursor
						.element().equals(String.valueOf(i)));
				cursor = l.prev(cursor);
				if (i == 999)
					fail("Error_ El método prev() debería haber lanzado la excepción BoundaryViolationException al invocarlo con la primer posición de la lista en el testeo del método addFirst()");
			}
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar prev() con una posición válida en el testeo del método addFirst()");
		} catch (BoundaryViolationException e2) {
			if (i != 999)
				fail("Error_ BoundaryViolationException al invocar prev() con una posición distinta a la primer posición de la lista en el testeo del método addFirst()");
		} catch (EmptyListException e2) {fail("Al solicitar la última posición de una lista que no está vacía lanza la excepción EmptyListException");}


	}

	/*
	 * _______________________TESTEAMOS EL METODO addLast()___________________________ */

	@Test
	public void addLast() {
		Position<String> cursor = null;
		int i = 0;
		try {
			// Caso_de_prueba: Lista con varios elementos.(Hacia adelante)
			for (i = 0; i < 1000; i++)
				l.addLast(String.valueOf(i));
			cursor = l.first();
			for (i = 0; i <= 999; i++) {
				assertTrue("addLast() no funciona correctamente", cursor
						.element().equals(String.valueOf(i)));
				cursor = l.next(cursor);
				if (i == 999)
					fail("Error_ El método next() debería haber lanzado la excepción BoundaryViolationException al invocarlo con la última posición de la lista en el testeo del método addLast()");
			}
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar next() con una posición válida en el testeo del método addLast()");
		} catch (BoundaryViolationException e2) {
			if (i != 999)
				fail("Error_ BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista en el testeo del método addLast()");
		} catch (EmptyListException e2) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}

		try {
			// Caso_de_prueba: Lista con varios elementos.(Hacia atrás)
			cursor = l.last();
			for (i = 999; i >= 0; i--) {
				assertTrue("addLast() no funciona correctamente", cursor
						.element().equals(String.valueOf(i)));
				cursor = l.prev(cursor);
				if (i == 0)
					fail("Error_ El método prev() debería haber lanzado la excepción BoundaryViolationException al invocarlo con la primer posición de la lista en el testeo del método addLast()");
			}
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar prev() con una posición válida en el testeo del método addLast()");
		} catch (BoundaryViolationException e2) {
			if (i != 0)
				fail("Error_ BoundaryViolationException al invocar prev() con una posición distinta a la primer posición de la lista en el testeo del método addLast()");
		} catch (EmptyListException e2) {fail("Al solicitar la última posición de una lista que no está vacía lanza la excepción EmptyListException");}

	}

	/*
	 * _______________________TESTEAMOS EL METODO addAfter()___________________________ */

	@Test
	public void addAfter() {
		Position<String> cursor = null;
		// Caso_de_prueba: Lista Vacía.
		try {
			l.addAfter(l.first(), n1);
			fail("Al invocar first() sobre una lista vacía debería lanzarse la excepción EmptyListException");
		} catch (InvalidPositionException e) {
		} catch (EmptyListException e2) {}
		try {
			l.addAfter(null, n1);
			fail("Al invocar addAfter() sobre una lista vacía debería lanzarse la excepción InvalidPositionException");
		} catch (InvalidPositionException e) {
		} 

		l.addFirst(n1);
		// Caso_de_prueba: Posición Inválida.
		try {
			l.addAfter(null, n1);
			fail("Al invocar addAfter() con una posición nula debería lanzarse la excepción InvalidPositionException");
		} catch (InvalidPositionException e) {
		}
		// Caso_de_prueba: Lista con varios elementos.
		try {
			l.addAfter(l.first(), n2);
			l.addAfter(l.last(), n3);
		} catch (InvalidPositionException e) {
			fail("addAfter() no debería lanzar la excepción InvalidPositionException con una posición válida");
		} catch (EmptyListException e2) {fail("Al solicitar la primer o última posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// Caso_de_prueba: Lista con varios elementos (Hacia adelante).
		try {
			cursor = l.first();
			assertTrue("addAfter() no funciona correctamente",
					cursor.element() == n1);
			cursor = l.next(cursor);
			assertTrue("addAfter() no funciona correctamente",
					cursor.element() == n2);
			cursor = l.next(cursor);
			assertTrue("addAfter() no funciona correctamente",
					cursor.element() == n3);
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar next() o addAfter() con una posición válida en el testeo del método addAfter()");
		} catch (BoundaryViolationException e2) {
			fail("Error_ BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista en el testeo del método addAfter()");
		} catch (EmptyListException e2) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}

		// Caso_de_prueba: Lista con varios elementos (Hacia atras).
		try {
			cursor = l.last();
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n3);
			cursor = l.prev(cursor);
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n2);
			cursor = l.prev(cursor);
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n1);
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar prev() o addAfter() con una posición válida en el testeo del método addAfter()");
		} catch (BoundaryViolationException e2) {
			fail("Error_ BoundaryViolationException al invocar prev() con una posición distinta a la primer posición de la lista en el testeo del método addAfter()");
		} catch (EmptyListException e2) {fail("Al solicitar la última posición de una lista que no está vacía lanza la excepción EmptyListException");}

		// Caso_de_prueba: Lista con varios elementos (Hacia adelante).
		try {    
			cursor = l.first();
			l.addAfter(l.first(), n4);
			cursor = l.next(cursor);
			cursor = l.next(cursor);
			l.addAfter(cursor, n5);
			cursor = l.next(cursor);
			cursor = l.next(cursor);
			l.addAfter(l.last(), n6);
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar next() o addAfter() con una posición válida en el testeo del método addAfter()");
		} catch (BoundaryViolationException e2) {
			fail("Error_ BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista en el testeo del método addAfter()");
		} catch (EmptyListException e2) {fail("Al solicitar la primer o última posición de una lista que no está vacía lanza la excepción EmptyListException");}

		try {
			cursor = l.first();
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n1);
			cursor = l.next(cursor);
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n4);
			cursor = l.next(cursor);
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n2);
			cursor = l.next(cursor);
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n5);
			cursor = l.next(cursor);
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n3);
			cursor = l.next(cursor);
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n6);

		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar next() o addAfter() con una posición válida en el testeo del método addAfter()");
		} catch (BoundaryViolationException e2) {
			fail("Error_ BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista en el testeo del método addAfter()");
		} catch (EmptyListException e2) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}

		// Caso_de_prueba: Lista con varios elementos (Hacia atras).
		try {
			cursor = l.last();
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n6);
			cursor = l.prev(cursor);
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n3);
			cursor = l.prev(cursor);
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n5);
			cursor = l.prev(cursor);
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n2);
			cursor = l.prev(cursor);
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n4);
			cursor = l.prev(cursor);
			assertTrue("AddAfter no funciona correctamente",
					cursor.element() == n1);
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar prev() o addAfter() con una posición válida en el testeo del método addAfter()");
		} catch (BoundaryViolationException e2) {
			fail("Error_ BoundaryViolationException al invocar prev() con una posición distinta a la primer posición de la lista en el testeo del método addAfter()");
		} catch (EmptyListException e2) {fail("Al solicitar la última posición de una lista que no está vacía lanza la excepción EmptyListException");}


	}

	/*
	 * _______________________TESTEAMOS EL METODO addAfter()____________________________ */

	@Test
	public void addBefore() {
		Position<String> cursor = null;
		// Caso_de_prueba: Lista Vacía.
		try {
			l.addBefore(l.first(), n1);
			fail("Al invocar first() sobre una lista vacía debería lanzarse la excepción EmptyListException");
			fail("Al invocar addBefore() sobre una lista vacía debería lanzarse la excepción InvalidPositionException");			
		} catch (InvalidPositionException e) {
		} catch (EmptyListException e2) {}
		try {
			l.addBefore(null, n1);
			fail("Al invocar addBefore() sobre una lista vacía debería lanzarse la excepción InvalidPositionException");			
		} catch (InvalidPositionException e) {
		} 
		l.addFirst(n1);
		// Caso_de_prueba: Posición inválida.
		try {
			l.addBefore(null, n1);
			fail("Al invocar addBefore() con una posición nula debería lanzarse la excepción InvalidPositionException");
		} catch (InvalidPositionException e) {
		}
		// Caso_de_prueba: Lista con varios elementos (Hacia adelante).
		try {
			l.addBefore(l.first(), n2);
			l.addBefore(l.last(), n3);
		} catch (InvalidPositionException e) {
			fail("addBefore() no debería lanzar la excepción InvalidPositionException con una posición válida");
		} catch (EmptyListException e2) {fail("Al solicitar la primer o última posición de una lista que no está vacía lanza la excepción EmptyListException");}
		try {
			cursor = l.first();
			assertTrue("addBefore() no funciona correctamente",
					cursor.element() == n2);
			cursor = l.next(cursor);
			assertTrue("addBefore() no funciona correctamente",
					cursor.element() == n3);
			cursor = l.next(cursor);
			assertTrue("addBefore() no funciona correctamente",
					cursor.element() == n1);
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar next() o addBefore() con una posición válida en el testeo del método addBefore()");
		} catch (BoundaryViolationException e2) {
			fail("Error_ BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista en el testeo del método addBefore()");
		} catch (EmptyListException e2) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// Caso_de_prueba: Lista con varios elementos (Hacia atrás).
		try {
			cursor = l.last();
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n1);
			cursor = l.prev(cursor);
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n3);
			cursor = l.prev(cursor);
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n2);
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar prev() o addBefore() con una posición válida en el testeo del método addBefore()");
		} catch (BoundaryViolationException e2) {
			fail("Error_ BoundaryViolationException al invocar prev() con una posición distinta a la primer posición de la lista en el testeo del método addBefore()");
		} catch (EmptyListException e2) {fail("Al solicitar la última posición de una lista que no está vacía lanza la excepción EmptyListException");}
		try {
			cursor = l.first();
			l.addBefore(l.first(), n4);
			cursor = l.next(cursor);
			l.addBefore(cursor, n5);
			l.addBefore(l.last(), n6);
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar next() o addBefore() con una posición válida en el testeo del método addBefore()");
		} catch (BoundaryViolationException e2) {
			fail("Error_ BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista en el testeo del método addBefore()");
		} catch (EmptyListException e2) {fail("Al solicitar la última y primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// Caso_de_prueba: Lista con varios elementos (Hacia adelante).
		try {
			cursor = l.first();
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n4);
			cursor = l.next(cursor);
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n2);
			cursor = l.next(cursor);
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n5);
			cursor = l.next(cursor);
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n3);
			cursor = l.next(cursor);
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n6);
			cursor = l.next(cursor);
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n1);

		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar next() o addBefore() con una posición válida en el testeo del método addBefore()");
		} catch (BoundaryViolationException e2) {
			fail("Error_ BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista en el testeo del método addBefore()");
		} catch (EmptyListException e2) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// Caso_de_prueba: Lista con varios elementos (Hacia atrás).
		try {
			cursor = l.last();
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n1);
			cursor = l.prev(cursor);
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n6);
			cursor = l.prev(cursor);
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n3);
			cursor = l.prev(cursor);
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n5);
			cursor = l.prev(cursor);
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n2);
			cursor = l.prev(cursor);
			assertTrue("addBefore no funciona correctamente",
					cursor.element() == n4);
		} catch (InvalidPositionException e) {
			fail("Error_ InvalidPositionException al invocar prev() o addBefore() con una posición válida en el testeo del método addBefore()");
		} catch (BoundaryViolationException e2) {
			fail("Error_ BoundaryViolationException al invocar prev() con una posición distinta a la primer posición de la lista en el testeo del método addBefore()");
		} catch (EmptyListException e2) {fail("Al solicitar la última posición de una lista que no está vacía lanza la excepción EmptyListException");}
	}

	/*
	 * _______________________TESTEAMOS EL METODO remove()________________________ */

	@Test
	public void remove() {
		Position<String> cursor;
		int i = 5;
		l.addFirst(n1);
		cursor=null;
		try {
			cursor = l.first();
			// Caso de prueba: posición inválida, lista no vacía.
			l.remove(null);
			fail("Al invocar remove() con una posición inválida debería lanzarse la excepción InvalidPositionException");
		} catch (InvalidPositionException e) {
		} catch (EmptyListException e2) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// Caso de prueba: eliminar el único elemento de la lista
		try {
			l.remove(cursor);
		} catch (InvalidPositionException e) {
			fail("remove() no debería lanzar la excepción InvalidPositionException con una posición válida");
		}
		// Caso de prueba: lista vacía.
		try {
			l.remove(cursor);
			fail("Al invocar remove() sobre una lista vacía debería lanzar la excepción InvalidPositionException.");
		} catch (InvalidPositionException e) {
		}
		// Inserto 5 elementos en la lista.
		for (i = 1; i < 6; i++)
			l.addLast(String.valueOf(i));
		try {    
			cursor = l.first();
			// CASO_DE_PRUEBA:Elimino el primer elemento de una lista no vacía.
			l.remove(cursor);
			cursor = l.first();
		} catch (InvalidPositionException e) {
			fail("remove() no debería lanzar la excepción InvalidPositionException con una posición válida.");
		} catch (EmptyListException e2) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// verifico que la estructura sea correcta del principio hasta adelante.

		try {
			for (i = 2; i < 6; i++) {
				assertTrue("remove() no funciona correctamente", cursor
						.element().equals(String.valueOf(i)));
				cursor = l.next(cursor);
				if (i == 5)
					fail("Error_Debería lanzarse la excepción BoundaryViolationException al invocar next() con la última posición de la lista testeando el método remove().");
			}
		} catch (InvalidPositionException e) {
			fail("Error_InvalidPositionException al invocar next() con una posición válida testeando el método remove()");
		} catch (BoundaryViolationException e2) {
			if (i != 5)
				fail("Error_BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista testeando el método remove()");
		}
		// verifico que la estructura sea correcta hacia atrás
		try {
			cursor = l.last();
			for (i = 5; i > 1; i--) {
				assertTrue("remove() no funciona correctamente", cursor
						.element().equals(String.valueOf(i)));
				cursor = l.prev(cursor);
				if (i == 2)
					fail("Error_ El método prev() debería haber lanzado la excepción BoundaryViolationException al invocarlo con la primer posición de la lista en el testeo del método remove()");
			}
		} catch (InvalidPositionException e) {
			fail("Error_InvalidPositionException al invocar prev() con una posición válida testeando el método remove()");
		} catch (BoundaryViolationException e2) {
			if (i != 2)
				fail("Error_BoundaryViolationException al invocar prev() con una posición distinta a la primer posición de la lista testeando el método remove()");
		} catch (EmptyListException e2) {fail("Al solicitar la última posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// CASO_DE_PRUEBA:Elimino el último elemento de una lista no vacía.
		try {
			cursor = l.last();
			l.remove(cursor);
		} catch (InvalidPositionException e) {
			fail("remove() no debería lanzar la excepción InvalidPositionException con una posición válida");
		} catch (EmptyListException e2) {fail("Al solicitar la última posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// verifico que la estructura sea correcta del principio hasta el final.
		try {
			cursor = l.first();
			for (i = 2; i < 5; i++) {
				assertTrue("remove() no funciona correctamente", cursor
						.element().equals(String.valueOf(i)));
				cursor = l.next(cursor);
				if (i == 4)
					fail("Error_Debería lanzarse la excepción BoundaryViolationException al invocar next() con la última posición de la lista testeando el método remove().");
			}
		} catch (InvalidPositionException e) {
			fail("Error_InvalidPositionException al invocar next() con una posición válida testeando el método remove()");
		} catch (BoundaryViolationException e2) {
			if (i != 4)
				fail("Error_BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista testeando el método remove()");
		} catch (EmptyListException e2) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		try {
			// verifico que la estructura sea correcta hacia atrás
			cursor = l.last();
			for (i = 4; i > 1; i--) {
				assertTrue("remove() no funciona correctamente", cursor
						.element().equals(String.valueOf(i)));
				cursor = l.prev(cursor);
				if (i == 2)
					fail("Error_ El método prev() debería haber lanzado la excepción BoundaryViolationException al invocarlo con la primer posición de la lista en el testeo del método remove()");
			}
		} catch (InvalidPositionException e) {
			fail("Error_InvalidPositionException al invocar prev() con una posición válida testeando el método remove()");
		} catch (BoundaryViolationException e2) {
			if (i != 2) 				
				fail("Error_BoundaryViolationException al invocar prev() con una posición distinta a la primer posición de la lista testeando el método remove()");
		} catch (EmptyListException e2) {fail("Al solicitar la última posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// CASO_DE_PRUEBA:Elimino el elemento del medio

		try {
			cursor = l.first();
			cursor = l.next(cursor);
			l.remove(cursor);
		} catch (InvalidPositionException e) {
			fail("remove() no debería lanzar la excepción InvalidPositionException con una posición válida");
		} catch (BoundaryViolationException e) {
			fail("Error_BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista testeando el método remove()");
		} catch (EmptyListException e2) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// verifico que la estructura sea correcta hacia adelante.
		try {
			cursor = l.first();
			assertTrue("remove() no funciona correctamente", cursor.element()
					.equals(String.valueOf(2)));
			cursor = l.next(cursor);
			assertTrue("remove() no funciona correctamente", cursor.element()
					.equals(String.valueOf(4)));
		} catch (InvalidPositionException e) {
			fail("Error_InvalidPositionException al invocar next() con una posición válida testeando el método remove()");
		} catch (BoundaryViolationException e2) {
			if (i != 4)
				fail("Error_BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista testeando el método remove()");
		} catch (EmptyListException e2) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
	}

	/*
	 * _______________________TESTEAMOS EL METODO set()_____________________________ */

	@Test
	public void set() {
		int i;
		Position<String> cursor;
		String el, el2;
		l.addFirst(n1);
		cursor=null;
		try {
			cursor = l.first();
			// Caso de prueba: posición inválida, lista no vacía.
			l.set(null, n2);
			fail("Al invocar set() con una posición inválida debería lanzar la excepción InvalidPositionException");
		} catch (InvalidPositionException e) {
		} catch (EmptyListException e) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		// Caso de prueba: set el único elemento de la lista
		try {
			el = (String) l.set(cursor, n2);
			assertTrue("set() no funciona correctamente", el.equals(n1));
			assertTrue("set() no funciona correctamente", cursor.element()
					.equals(n2));
		} catch (InvalidPositionException e) {
			fail(" set() no debería lanzar la excepción InvalidPositionException con una posición válida.");
		}
		// Caso de prueba: lista vacía.
		try {
			l.remove(cursor);

		} catch (InvalidPositionException e) {
			fail("Error_InvalidPositionException al invocar remove() con una posición válida testeando el método set()");
		}
		try {
			l.set(cursor, n1);
			fail("Al invocar set() sobre una lista vacía debería lanzar la excepción InvalidPositionException");
		} catch (InvalidPositionException e) {
		}
		// Caso de prueba: muchos sets.
		for (i = 1; i < 10000; i++)
			l.addLast(String.valueOf(i));
		try {
			cursor = l.first();
			for (i = 1; i < 10000; i++) {
				el2 = l.set(cursor, String.valueOf(i + 10));
				assertTrue("set() no funciona correctamente",
						el2.equals(String.valueOf(i)));
				assertTrue("set() no funciona correctamente", cursor.element()
						.equals(String.valueOf(i + 10)));
				cursor = l.next(cursor);
				if (i == 9999)
					fail("Error_Debería lanzarse la excepción BoundaryViolationException al invocar next() con la última posición de la lista testeando el método set().");
			}
		} catch (InvalidPositionException e) {
			fail("Error_InvalidPositionException al invocar next() con una posición válida testeando el método set()");
		} catch (BoundaryViolationException e) {
			if (i != 9999)
				fail("Error_BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista testeando el método set()");
		} catch (EmptyListException e) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		try {
			cursor = l.first();
			for (i = 11; i < 10010; i++) {
				assertTrue("set() no funciona correctamente", cursor.element()
						.equals(String.valueOf(i)));
				cursor = l.next(cursor);
				if (i == 10009)
					fail("Error_Debería lanzarse la excepción BoundaryViolationException al invocar next() con la última posición de la lista testeando el método set().");
			}
		} catch (InvalidPositionException e) {
			fail("Error_InvalidPositionException al invocar next() con una posición válida testeando el método set()");
		} catch (BoundaryViolationException e) {
			if (i != 10009) {
				fail("Error_BoundaryViolationException al invocar next() con una posición distinta a la última posición de la lista testeando el método set()");
			}
		} catch (EmptyListException e) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}
		try {
			cursor = l.last();
			for (i = 10009; i > 10; i--) {
				assertTrue("set() no funciona correctamente", cursor.element()
						.equals(String.valueOf(i)));
				cursor = l.prev(cursor);
				if (i == 11)
					fail("Error_ El método prev() debería haber lanzado la excepción BoundaryViolationException al invocarlo con la primer posición de la lista en el testeo del método set()");
			}
		} catch (InvalidPositionException e) {
			fail("Error_InvalidPositionException al invocar prev() con una posición válida testeando el método set()");
		} catch (BoundaryViolationException e) {
			if (i != 11)
				fail("Error_BoundaryViolationException al invocar prev() con una posición distinta a la primer posición de la lista testeando el método set()");
		} catch (EmptyListException e) {fail("Al solicitar la última posición de una lista que no está vacía lanza la excepción EmptyListException");}
	}

	/*
	 * _______________________TESTEAMOS EL METODO iterator()_____________________________ */

	@Test
	public void iterator() {
		Set<String> C= new HashSet<String>();
		Iterator<String> it;
		int i = 0;
		it = l.iterator();
		String element;
		// Caso_De_Prueba: lista vacía.
		assertTrue("El iterador debe estar vacío", it.hasNext() == false);
		// Caso_De_Prueba: lista con un solo elemento.
		l.addFirst(n1);
		it = l.iterator();
		assertTrue("El iterador no debe estar vacío", it.hasNext() == true);
		assertTrue("El iterador no funciona correctamente", it.next()
				.equals(n1));
		assertTrue("El iterador debe estar vacío", it.hasNext() == false);
		try {
			while (!l.isEmpty()) {
				l.remove(l.first());
			}
		} catch (InvalidPositionException e) {
			fail("Error_InvalidPositionException al invocar remove() con una posición válida testeando el método iterator()");
		} catch (EmptyListException e) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}

		// Caso_De_Prueba: Lista con mas de un elemento
		for (i = 1; i < 10000; i++)
		{l.addLast(String.valueOf(i));
		C.add(String.valueOf(i));
		}

		it = l.iterator();
		i = 1;
		while (it.hasNext()) {
			element=it.next();
			assertTrue("El iterador no funciona correctamente", C.contains(element));
			assertTrue("El iterador no funciona correctamente", C.remove(element));		
			i++;
		}
		assertTrue("El iterador no recorre todos los elementos", C.isEmpty());
	}
	/*
	 * _______________________TESTEAMOS EL METODO positions()_____________________________ */

	@Test
	public void positions() {
		Set<Position<String>> C= new HashSet<Position<String>>();
		Iterator<Position<String>> it;
		int i = 0;
		it = l.positions().iterator();
		Position<String> element;
		// Caso_De_Prueba: lista vacía.
		assertTrue("El iterador debe estar vacío", it.hasNext() == false);
		// Caso_De_Prueba: lista con un solo elemento.
		l.addFirst(n1);
		it = l.positions().iterator();
		assertTrue("El iterador no debe estar vacío", it.hasNext() == true);
		assertTrue("El iterador no funciona correctamente", it.next().element()
				.equals(n1));
		assertTrue("El iterador debe estar vacío", it.hasNext() == false);
		try {
			while (!l.isEmpty()) {
				l.remove(l.first());
			}
		} catch (InvalidPositionException e) {
			fail("Error_InvalidPositionException al invocar remove() con una posición válida testeando el método iterator()");
		} catch (EmptyListException e) {fail("Al solicitar la primer posición de una lista que no está vacía lanza la excepción EmptyListException");}

		// Caso_De_Prueba: Lista con mas de un elemento
		for (i = 1; i < 10000; i++)
		{l.addLast(String.valueOf(i));
		try {
			C.add(l.last());
		} catch (EmptyListException e) {
			fail("Error_EmptyListException al invocar last() en una lista no vacía()");
		}
		}

		it = l.positions().iterator();
		i = 1;
		while (it.hasNext()) {
			element=it.next();
			assertTrue("El iterador no funciona correctamente", C.contains(element));
			assertTrue("El iterador no funciona correctamente", C.remove(element));		
			i++;
		}
		assertTrue("El iterador no recorre todos los elementos", C.isEmpty());
	}
}