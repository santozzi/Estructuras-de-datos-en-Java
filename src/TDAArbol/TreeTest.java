
package TDAArbol;
import static org.junit.Assert.*;


import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import TDACola.*;
import TDALista.*;


public class TreeTest {

	private Tree<Integer> T; // interface

	/*
	 * Inicializa la cola antes de cada test individual
	 */

	private Tree<Integer> getTree() {
		return new ArbolConLista<Integer>();
	}

	@Before
	public void setUp() {
		T = getTree();
	}

	@Test
	public void size() {
		Position<Integer> h1, h2, h3, h4;
		h1 = h2 = h3 = h4 = null;
		// Caso_de_prueba: Arbol Vacío.
		assertTrue("Tamaño del árbol justo después de ser creado != 0",
				T.size() == 0);
		// Caso_de_prueba: Solo raíz.
		try {
			T.createRoot(1);
			assertTrue("Tamaño del árbol con 1 elemento != 1", T.size() == 1);
		} catch (InvalidOperationException e) {
			fail("Al crear la raíz de un árbol vacío lanza la excepción InvalidOperationException");
		}
		try {
			h1 = T.addFirstChild(T.root(), 2);
			assertTrue("Tamaño del árbol con 2 elementos != 2", T.size() == 2);
		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(root,e) lanza la excepción InvalidPositionException");
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raíz de un árbol no vacío lanza la excepción EmptyTreeException");
		}
		try {
			h4 = T.addLastChild(T.root(), 5);
			assertTrue("Tamaño del árbol con 3 elementos != 3", T.size() == 3);
		} catch (InvalidPositionException e) {
			fail("Al invocar addLastChild(root,e) lanza la excepción InvalidPositionException");
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raíz de un árbol no vacío lanza la excepción EmptyTreeException");
		}
		try {
			h2 = T.addAfter(T.root(), h1, 3);
			assertTrue("Tamaño del árbol con 4 elementos != 4", T.size() == 4);
		} catch (InvalidPositionException e) {
			fail("Al invocar addAfter(root,primerHijo,e) lanza la excepción InvalidPositionException");
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raíz de un árbol no vacío lanza la excepción EmptyTreeException");
		}
		try {
			h3 = T.addBefore(T.root(), h4, 4);
			assertTrue("Tamaño del árbol con 5 elementos != 5", T.size() == 5);
		} catch (InvalidPositionException e) {
			fail("Al invocar addBefore(root,UltimoHijo,e) lanza la excepción InvalidPositionException");
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raíz de un árbol no vacío lanza la excepción EmptyTreeException");
		}
		try {
			T.addFirstChild(h3, 8);
			assertTrue("Tamaño del árbol con 6 elementos != 6", T.size() == 6);
			T.addFirstChild(h3, 7);
			assertTrue("Tamaño del árbol con 7 elementos != 7", T.size() == 7);
		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(h3,e) lanza la excepción InvalidPositionException");
		}
		// Eliminando
		try {
			T.removeInternalNode(h3);
			assertTrue("Tamaño del árbol con 6 elementos != 6", T.size() == 6);
		} catch (InvalidPositionException e) {
			fail("Al invocar removeInternalNode con una posición válida lanza la excepción InvalidPositionException (testing size())");
		}
		try {
			T.removeExternalNode(h2);
			assertTrue("Tamaño del árbol con 5 elementos != 5", T.size() == 5);
		} catch (InvalidPositionException e) {
			fail("Al invocar removeExternalNode con una posición válida lanza la excepción InvalidPositionException (testing size())");
		}
		try {
			T.removeNode(h4);
			assertTrue("Tamaño del árbol con 4 elementos != 4", T.size() == 4);
		} catch (InvalidPositionException e) {
			fail("Al invocar removeNode con una posición válida lanza la excepción InvalidPositionException (testing size())");
		}

	}

	@Test
	public void isEmpty() {
		Position<Integer> h1, h2, h3, h4;
		h1 = h2 = h3 = h4 = null;
		// Caso_de_prueba: Arbol Vacío.
		assertTrue("El árbol justo después de ser creado no está vacío",
				T.isEmpty());
		// Caso_de_prueba: Solo raíz.
		try {
			T.createRoot(1);
			assertFalse("El árbol con 1 elemento está vacío", T.isEmpty());
		} catch (InvalidOperationException e) {
			fail("Al crear la raíz de un árbol vacío lanza la excepción InvalidOperationException");
		}
		try {
			h1 = T.addFirstChild(T.root(), 2);
			assertFalse("El árbol con 2 elementos está vacío", T.isEmpty());
		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(root,e) lanza la excepción InvalidPositionException");
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raíz de un árbol no vacío lanza la excepción EmptyTreeException");
		}
		try {
			h4 = T.addLastChild(T.root(), 5);
			assertFalse("El árbol con 3 elementos está vacío", T.isEmpty());
		} catch (InvalidPositionException e) {
			fail("Al invocar addLastChild(root,e) lanza la excepción InvalidPositionException");
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raíz de un árbol no vacío lanza la excepción EmptyTreeException");
		}
		try {
			h2 = T.addAfter(T.root(), h1, 3);
			assertFalse("El árbol con 4 elementos está vacío", T.isEmpty());
		} catch (InvalidPositionException e) {
			fail("Al invocar addAfter(root,primerHijo,e) lanza la excepción InvalidPositionException");
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raíz de un árbol no vacío lanza la excepción EmptyTreeException");
		}
		try {
			h3 = T.addBefore(T.root(), h4, 4);
			assertFalse("El árbol con 5 elementos está vacío", T.isEmpty());
		} catch (InvalidPositionException e) {
			fail("Al invocar addBefore(root,UltimoHijo,e) lanza la excepción InvalidPositionException");
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raíz de un árbol no vacío lanza la excepción EmptyTreeException");
		}
		try {
			T.addFirstChild(h3, 8);
			assertFalse("El árbol con 6 elementos está vacío", T.isEmpty());
			T.addFirstChild(h3, 7);
			assertFalse("El árbol con 7 elementos está vacío", T.isEmpty());
		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(h3,e) lanza la excepción InvalidPositionException");
		}
		// Eliminando
		try {
			T.removeInternalNode(h3);
			assertFalse("El árbol con 6 elementos está vacío", T.isEmpty());
		} catch (InvalidPositionException e) {
			fail("Al invocar removeInternalNode con una posición válida lanza la excepción InvalidPositionException (testing size())");
		}
		try {
			T.removeExternalNode(h2);
			assertFalse("El árbol con 5 elementos está vacío", T.isEmpty());
		} catch (InvalidPositionException e) {
			fail("Al invocar removeExternalNode con una posición válida lanza la excepción InvalidPositionException (testing size())");
		}
		try {
			T.removeNode(h4);
			assertFalse("El árbol con 4 elementos está vacío", T.isEmpty());
		} catch (InvalidPositionException e) {
			fail("Al invocar removeNode con una posición válida lanza la excepción InvalidPositionException (testing size())");
		}

	}

	@Test
	public void iterator() {
		Iterator<Integer> it;
		int i = 1;
		// Caso_De_Prueba: Arbol vacío.
		it = T.iterator();
		assertTrue("El iterador debe estar vacío", it.hasNext() == false);
		// Caso_De_Prueba: Arbol con un solo elemento.
		try {
			T.createRoot(1);
			it = T.iterator();
		} catch (InvalidOperationException e) {
			fail("Al invocar createRoot() con un árbol vacío lanza la excepción InvalidOperationException");
		}
		assertTrue("El iterador no debe estar vacío", it.hasNext() == true);
		assertTrue("El iterador no funciona correctamente", it.next().equals(1));
		assertTrue("El iterador debe estar vacío", it.hasNext() == false);

		// Caso_De_Prueba: Arbol con mas de un elemento
		T = getTree();
		cargarArbol(T);
		it = T.iterator();
		while (it.hasNext()) {
			assertTrue("El iterador no funciona correctamente", it.next()
					.equals(i));
			i++;
		}
		assertTrue("El iterador no funciona correctamente", i == T.size() + 1);
	}

	@Test
	public void positions() {
		Iterator<Position<Integer>> it;
		int i = 1;
		// Caso_De_Prueba: Arbol vacío.
		it = T.positions().iterator();
		assertTrue("El iterador debe estar vacío", it.hasNext() == false);
		// Caso_De_Prueba: Arbol con un solo elemento.
		try {
			T.createRoot(1);
			it = T.positions().iterator();
		} catch (InvalidOperationException e) {
			fail("Al invocar createRoot() con un árbol vacío lanza la excepción InvalidOperationException");
		}
		assertTrue("El iterador no debe estar vacío", it.hasNext() == true);
		assertTrue("El iterador no funciona correctamente", it.next().element()
				.equals(1));
		assertTrue("El iterador debe estar vacío", it.hasNext() == false);

		// Caso_De_Prueba: Arbol con mas de un elemento
		T = getTree();
		cargarArbol(T);
		it = T.positions().iterator();
		while (it.hasNext()) {
			assertTrue("El iterador no funciona correctamente", it.next()
					.element().equals(i));
			i++;
		}
		assertTrue("El iterador no funciona correctamente", i == T.size() + 1);
	}

	@Test
	public void replace() {
		Position<Integer> h1;
		Iterator<Position<Integer>> hijos;
		h1 = null;
		// Posición Inválida
		try {
			T.replace(null, 0);
			fail("Replace() debería lanzar la excepción InvalidPositionException con una posición Inválida");
		} catch (InvalidPositionException e) {
		}
		// Arbol con 1 solo elemento
		try {
			T.createRoot(1);
			T.replace(T.root(), 2);
			assertTrue(
					"Replace no funciona correctamente para un árbol con un solo elemento.",
					T.root().element().equals(2));
		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar InvalidOperationException con un árbol vacío");
		} catch (InvalidPositionException e2) {
			fail("replace() no debería lanzar InvalidPositionException con una posición válida.");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar EmptyTreeException con un árbol con elementos");
		}
		// Arbol con varios elementos
		T = getTree();
		cargarArbol(T);
		// nodo interno
		try {
			hijos = T.children(T.root()).iterator();
			// nodo hoja
			h1 = hijos.next();
			assertTrue("Arbol mal armado", h1.element().equals(2));
			T.replace(h1, 10);
			assertTrue("Replace no funciona correctamente", h1.element()
					.equals(10));
			// Nodo interno
			h1 = hijos.next();
			assertTrue("Arbol mal armado", h1.element().equals(3));
			T.replace(h1, 10);
			assertTrue("Replace no funciona correctamente", h1.element()
					.equals(10));
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raíz de un árbol no vacío lanza la excepción EmptyTreeException");
		} catch (InvalidPositionException e2) {
			fail("replace() no debería lanzar InvalidPositionException con una posición válida.");
		}

	}

	@Test
	public void root() {
		// Arbol Vacio
		try {
			T.root();
			fail("root() debería haber lanzado la excepción EmptyTreeException con un árbol vacío");
		} catch (EmptyTreeException e) {
		}
		// Arbol 1 elemento
		try {
			T.createRoot(1);
			assertTrue(
					"root() no funciona correctamente para un árbol con un solo elemento.",
					T.root().element().equals(1));
		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar InvalidOperationException con un árbol vacío");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar EmptyTreeException con un árbol con elementos");
		}
		// Arbol con mas de 1 elemento
		T = getTree();
		cargarArbol(T);
		try {
			assertTrue(
					"root() no funciona correctamente para un árbol con muchos solo elementos.",
					T.root().element().equals(1));
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar EmptyTreeException con un árbol con elementos");
		}
	}

	@Test
	public void parent() {
		// Posición Inválida
		try {
			T.parent(null);
			fail("parent() debería lanzar la excepción InvalidPositionException al invocarse con una posición inválida");
		} catch (InvalidPositionException e) {
		} catch (BoundaryViolationException e) {
			fail("parent() no debería lanzar la excepción BoundaryViolationException con una posición distinta a la de la raíz");
		}
		// Raíz
		T = getTree();
		cargarArbol(T);

		try {
			T.parent(T.root());
			fail("parent() debería lanzar la excepción BoundaryViolationException al invocarse con la posición de la raíz");
		} catch (InvalidPositionException e1) {
			fail("parent() no debería lanzar la excepción InvalidPositionException con una posición válida");
		} catch (BoundaryViolationException e2) {
		} catch (EmptyTreeException e3) {
			fail("root() no debería lanzar EmptyTreeException con un árbol con elementos");
		}

		try {
			chequearPadre(T.root());
		} catch (EmptyTreeException e3) {
			fail("root() no debería lanzar EmptyTreeException con un árbol con elementos");
		}
	}

	@Test
	public void children() {
		Iterator<Position<Integer>> hijos;
		// Posición Inválida
		try {
			T.children(null);
			fail("children() debería lanzar la excepción InvalidPositionException con una posición Inválida");
		} catch (InvalidPositionException e) {
		}
		// Arbol con 1 solo elemento
		try {
			T.createRoot(1);
			hijos = T.children(T.root()).iterator();
			assertTrue(
					"children() no funciona correctamente para un árbol con un solo elemento, la lista de hijos de la raíz no es vacía.",
					!hijos.hasNext());
		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar InvalidOperationException con un árbol vacío");
		} catch (InvalidPositionException e2) {
			fail("children() no debería lanzar InvalidPositionException con una posición válida.");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar EmptyTreeException con un árbol con elementos");
		}
		// Arbol con varios elementos
		T = getTree();
		hijos = null;
		cargarArbol2(T);
		// chequearHijosNiveles();
		Position<Integer> p;
		int i = 1;
		Queue<Position<Integer>> cola = new ArrayQueue<Position<Integer>>();
		try {
			p = T.root();
		} catch (EmptyTreeException e1) {
			fail("root() no debería lanzar la excepción EmptyTreeException con un árbol con elementos.");
			p = null;
		}
		if (p != null) {
			cola.enqueue(p);
			cola.enqueue(null);
			while (!cola.isEmpty()) {
				try {
					p = cola.dequeue();
				} catch (EmptyQueueException e) {
					fail("dequeue() no debería lanzar EmptyQueueException para una cola con elementos.");
				}
				if (p == null) {
					if (!cola.isEmpty())
						cola.enqueue(null);
				} else {
					assertTrue("children() no funciona correctamente.", p
							.element().equals(i));
					i++;
					try {
						hijos = T.children(p).iterator();
						if (T.isExternal(p))
							assertFalse(
									"children no funciona correctamente para listas de hijos vacías.",
									hijos.hasNext());
						else {
							assertTrue(
									"children no funciona correctamente para listas de hijos vacías.",
									hijos.hasNext());
							while (hijos.hasNext())
								cola.enqueue(hijos.next());
						}
					} catch (InvalidPositionException e) {
						fail("children() no debería lanzar la excepción InvalidPositionException para una posición válida");
					}
				}

			}
			assertTrue("children() no funciona correctamente.",
					T.size() == (i - 1));
		}
	}

	@Test
	public void isInternalExternal() {
		cargarArbol(T);
		Position<Integer> p;
		Iterator<Position<Integer>> hijos = null;
		Queue<Position<Integer>> cola = new ArrayQueue<Position<Integer>>();
		// Posición inválida
		try {
			T.isExternal(null);
			fail("isExternal debería lanzar la excepción InvalidPositionException con una posición inválida");
		} catch (InvalidPositionException e) {
		}
		try {
			T.isInternal(null);
			fail("isInternal debería lanzar la excepción InvalidPositionException con una posición inválida");
		} catch (InvalidPositionException e) {
		}

		try {
			p = T.root();
		} catch (EmptyTreeException e1) {
			fail("root() no debería lanzar la excepción EmptyTreeException con un árbol con elementos.");
			p = null;
		}
		if (p != null) {
			cola.enqueue(p);
			cola.enqueue(null);
			while (!cola.isEmpty()) {
				try {
					p = cola.dequeue();
				} catch (EmptyQueueException e) {
					fail("dequeue() no debería lanzar EmptyQueueException para una cola con elementos.");
				}
				if (p == null) {
					if (!cola.isEmpty())
						cola.enqueue(null);
				} else {
					try {
						hijos = T.children(p).iterator();
						if (hijos.hasNext()) {
							assertTrue("isInternal no funciona correctamente.",
									T.isInternal(p));
							assertFalse(
									"isExternal no funciona correctamente.",
									T.isExternal(p));
						} else {
							assertTrue("isExternal no funciona correctamente.",
									T.isExternal(p));
							assertFalse(
									"isInternal no funciona correctamente.",
									T.isInternal(p));
							while (hijos.hasNext())
								cola.enqueue(hijos.next());
						}
					} catch (InvalidPositionException e) {
						fail("isInternal() o isExternal() no deberían lanzar la excepción InvalidPositionException para una posición válida");
					}
				}

			}
		}
	}

	@Test
	public void isroot() {
		cargarArbol(T);
		Position<Integer> p = null;
		Iterator<Position<Integer>> hijos = null;
		Queue<Position<Integer>> cola = new ArrayQueue<Position<Integer>>();
		try {
			T.isRoot(null);
			fail("isRoot debería lanzar la excepción InvalidPositionException con una posición inválida");
		} catch (InvalidPositionException e) {
		}

		try {
			p = T.root();
			assertTrue("isroot() no funciona correctamente", T.isRoot(p));
		} catch (EmptyTreeException e1) {
			fail("root() no debería lanzar la excepción EmptyTreeException con un árbol con elementos.");
			p = null;
		} catch (InvalidPositionException e1) {
			fail("root() no debería lanzar la excepción InvalidPositionException con una posición válida.");
		}
		if (p != null) {
			cola.enqueue(p);
			cola.enqueue(null);
			while (!cola.isEmpty()) {
				try {
					p = cola.dequeue();
				} catch (EmptyQueueException e) {
					fail("dequeue() no debería lanzar EmptyQueueException para una cola con elementos.");
				}
				if (p == null) {
					if (!cola.isEmpty())
						cola.enqueue(null);
				} else {
					try {

						hijos = T.children(p).iterator();
						while (hijos.hasNext()) {
							p = hijos.next();
							assertTrue("isroot() no funciona correctamente",
									!T.isRoot(p));
							cola.enqueue(p);
						}
					} catch (InvalidPositionException e) {
						fail("isInternal() o isExternal no deberían lanzar la excepción InvalidPositionException para una posición válida");
					}
				}

			}
		}
	}

	@Test
	public void createRoot() {
		try {
			T.createRoot(1);
			assertTrue("createRoot no funciona correctamente", T.root()
					.element().equals(1));
			assertTrue("createRoot no actualiza el size", T.size() == 1);
			assertFalse("createRoot no funciona correctamente",
					T.children(T.root()).iterator().hasNext());

		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar la excepción InvalidOperationException para un árbol vacío.");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío.");
		} catch (InvalidPositionException e) {
			fail("children() no debería lanzar la excepción InvalidPositionException para una posición válida.");
		}
		// Crear raiz de un árbol no vacío
		try {
			T.createRoot(2);
			fail("createRoot() debería haber lanzado la excepción InvalidOperationException sobre un árbol no vacío.");
		} catch (InvalidOperationException e) {
		}

	}

	@Test
	public void addFirstChild() {
		Position<Integer> p = null;
		Iterator<Position<Integer>> hijos;
		int i;
		// Agrego hijos a la raiz
		try {
			T.createRoot(1);
			p = T.root();
			T = getTree();
			T.addFirstChild(p, 2);
			fail("addFirstChild() debería lanzar la excepción InvalidPositionException cuando el árbol está vacío");
		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar la excepción InvalidOperationException para un árbol vacío.");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío.");
		} catch (InvalidPositionException e) {
		}
		try {
			T.createRoot(1);
			for (i = 2; i < 10; i++) {
				p = T.addFirstChild(T.root(), i);
				assertTrue(
						"addFirstChild() no actualiza correctamente el size ",
						T.size() == i);

			}
			chequearPadre(T.root());
		} catch (InvalidPositionException e) {
			fail("addFirstChild() no debería lanzar la excepción InvalidPositionException con una posición válida.");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío.");
		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar la excepción InvalidOperationException para un árbol vacío.");
		}

		try {
			hijos = T.children(T.root()).iterator();
			i = 9;
			while (hijos.hasNext()) {
				p = hijos.next();
				assertTrue("addFirstChild() no funciona correctamente", p
						.element().equals(i));
				i--;
			}
			assertTrue("addFirstChild() no funciona correctamente", i == 1);

		} catch (InvalidPositionException e) {
			fail("addFirstChild() no debería lanzar la excepción InvalidPositionException con una posición válida.");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío.");
		}
		// Agrego hijos al hijo extremo izquierdo de la raiz.
		try {
			for (i = 10; i < 20; i++) {
				T.addFirstChild(p, i);
				assertTrue(
						"addFirstChild() no actualiza correctamente el size ",
						T.size() == i);
			}
			chequearPadre(T.root());
		} catch (InvalidPositionException e) {
			fail("addFirstChild() no debería lanzar la excepción InvalidPositionException con una posición válida.");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío.");
		}

		try {
			hijos = T.children(p).iterator();
			i = 19;
			while (hijos.hasNext()) {
				p = hijos.next();
				assertTrue("addFirstChild() no funciona correctamente", p
						.element().equals(i));
				i--;
			}
			assertTrue("addFirstChild() no funciona correctamente", i == 9);
		} catch (InvalidPositionException e) {
			fail("addFirstChild() no debería lanzar la excepción InvalidPositionException con una posición válida.");
		}

	}

	@Test
	public void addLastChild() {
		Position<Integer> p = null;
		Iterator<Position<Integer>> hijos;
		int i;
		// Agrego hijos a la raiz
		try {
			T.createRoot(1);
			p = T.root();
			T = getTree();
			T.addLastChild(p, 2);
			fail("addLastChild() debería lanzar la excepción InvalidPositionException cuando el árbol está vacío");
		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar la excepción InvalidOperationException para un árbol vacío.");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío.");
		} catch (InvalidPositionException e) {
		}
		try {
			T.createRoot(1);
			for (i = 2; i < 10; i++) {
				T.addLastChild(T.root(), i);
				assertTrue(
						"addLastChild() no actualiza correctamente el size ",
						T.size() == i);
			}
			chequearPadre(T.root());
		} catch (InvalidPositionException e) {
			fail("addLastChild() no debería lanzar la excepción InvalidPositionException con una posición válida.");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío.");
		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar la excepción InvalidOperationException para un árbol vacío.");
		}
		try {
			hijos = T.children(T.root()).iterator();
			i = 2;
			while (hijos.hasNext()) {
				p = hijos.next();
				assertTrue("addLastChild() no funciona correctamente", p
						.element().equals(i));
				i++;
			}
			assertTrue("addLastChild() no funciona correctamente", i == 10);
		} catch (InvalidPositionException e) {
			fail("addLastChild() no debería lanzar la excepción InvalidPositionException con una posición válida.");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío.");
		}
		// Agrego hijos al hijo extremo izquierdo de la raiz.
		try {
			for (i = 10; i < 20; i++) {
				T.addLastChild(p, i);
				assertTrue(
						"addLastChild() no actualiza correctamente el size ",
						T.size() == i);
			}
			chequearPadre(T.root());
		} catch (InvalidPositionException e) {
			fail("addLastChild() no debería lanzar la excepción InvalidPositionException con una posición válida.");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío.");
		}

		try {
			hijos = T.children(p).iterator();
			i = 10;
			while (hijos.hasNext()) {
				p = hijos.next();
				assertTrue("addLastChild() no funciona correctamente", p
						.element().equals(i));
				i++;
			}
			assertTrue("addFirstChild() no funciona correctamente", i == 20);

		} catch (InvalidPositionException e) {
			fail("addFirstChild() no debería lanzar la excepción InvalidPositionException con una posición válida.");
		}

	}

	@Test
	public void addBefore() {
		Position<Integer> h1, h2, h3, h5, padre, hijo;
		h1 = h2 = h3 = h5 = padre = hijo = null;
		int tres = 0;
		PositionList<Integer> hijos, hijosRoot, hijosH1, hjsH1;
		try {
			T.createRoot(1);
			T.addBefore(null, T.root(), 2);
			fail("addBefore() debería lanzar la excepción InvalidPositionException con una posición inválida.");

		} catch (InvalidOperationException e1) {
			fail("createRoot() no debería lanzar la excepción InvalidOperationException para un árbol vacío.");
		} catch (EmptyTreeException e1) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío");
		} catch (InvalidPositionException e1) {

		}
		try {
			T = getTree();
			T.createRoot(4);
			padre = T.root();
			hijo = T.addFirstChild(padre, 5);
			T = getTree();
			T.addBefore(padre, hijo, 6);
			fail("addBefore() debería lanzar la excepcion InvalidPositionException cuando el árbol esta vacío");
		} catch (InvalidPositionException e1) {

		} catch (InvalidOperationException e) {

			fail("addFirstChild() no debería lanzar una excepción InvalidOperationException para un árbol vacío");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar una excepcion EmptyTreeException en un arbol no vacío");
		}
		try {

			T = getTree();
			T.createRoot(3);
			padre = T.root();
			T = getTree();
			T.createRoot(1);
			h1 = T.addFirstChild(T.root(), 2);
		} catch (InvalidPositionException e1) {
			fail("addFirstChild() no debería lanzar la excepción InvalidPositionException para una posición válida.");
		} catch (InvalidOperationException e1) {
			fail("createRoot() no debería lanzar la excepción InvalidOperationException para un árbol vacío.");
		} catch (EmptyTreeException e1) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío");
		}
		try {
			T.addBefore(padre, h1, 4);
			fail("addBefore() debería lanzar la excepcion InvalidPositionException cuando el nodo primer argumento no es el padre del nodo segundo argumento");
		} catch (InvalidPositionException e1) {
		}

		try {
			T = getTree();
			T.createRoot(1);
			h5 = T.addFirstChild(T.root(), 2);
			h1 = T.addBefore(T.root(), h5, 3);
			chequearPadre(T.root());
			// prueba para 3 como primer hijo de root
			tres = T.children(T.root()).iterator().next().element();
			assertTrue("addBefore() no funciona correctamente", tres == 3);
			h2 = T.addFirstChild(h1, 4);
			h3 = T.addBefore(T.root(), h1, 6);
			T.addBefore(T.root(), h5, 7);
			T.addBefore(T.root(), h3, 9);
			T.addBefore(h1, h2, 5);
			chequearPadre(T.root());
			// caso de prueba para los hijos de root
			hijosRoot = new DoubleLinkedList<Integer>();
			hijosRoot.addFirst(2);
			hijosRoot.addFirst(7);
			hijosRoot.addFirst(3);
			hijosRoot.addFirst(6);
			hijosRoot.addFirst(9);
			hijos = new DoubleLinkedList<Integer>();
			for (Position<Integer> n : T.children(T.root())) {
				hijos.addLast(n.element());
			}
			assertTrue("addBefore() no funciona correctamente",
					hijos.size() == hijosRoot.size());
			while (!hijos.isEmpty()) {
				assertTrue("addBefore() no funciona correctamente", hijos
						.first().element().equals(hijosRoot.first().element()));
				hijos.remove(hijos.first());
				hijosRoot.remove(hijosRoot.first());

			}
			// caso de prueba para los hijos de h1
			hijosH1 = new DoubleLinkedList<Integer>();
			hijosH1.addFirst(4);
			hijosH1.addFirst(5);
			hjsH1 = new DoubleLinkedList<Integer>();
			for (Position<Integer> n : T.children(h1)) {
				hjsH1.addLast(n.element());
			}
			assertTrue("addBefore() no funciona correctamente",
					hjsH1.size() == hijosH1.size());
			while (!hjsH1.isEmpty()) {
				assertTrue("addBefore() no funciona correctamente", hjsH1
						.first().element().equals(hijosH1.first().element()));
				hjsH1.remove(hjsH1.first());
				hijosH1.remove(hijosH1.first());
			}
		} catch (InvalidPositionException e1) {
			fail("addBefore(),children(), addFirstChild(), remove() no debería lanzar InvalidPositionException con una posición válida");
			e1.printStackTrace();
		} catch (EmptyTreeException e1) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío");
		} catch (InvalidOperationException e1) {
			fail("createRoot() no debería lanzar la excepción InvalidOperationException para un árbol vacío.");
		} catch (EmptyListException e1){fail("los métodos first() o last() no debería lanzar la excepción EmptyListException para una Lista con elementos [´PROBLEMA EN TDALISTA]");}

	}

	@Test
	public void addAfter() {
		Position<Integer> padre, hijo, h1, h2, h3, h5;
		padre = hijo = h1 = null;
		PositionList<Integer> hijosH1, hjsH1;
		try {
			T.createRoot(1);
			T.addAfter(null, T.root(), 2);
			fail("addAfter() debería lanzar la excepción InvalidPositionException con una posición inválida.");
		} catch (InvalidOperationException e1) {
			fail("createRoot() no debería lanzar la excepción InvalidOperationException para un árbol vacío.");
		} catch (EmptyTreeException e1) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío");
		} catch (InvalidPositionException e1) {
		}
		try {
			T = getTree();
			T.createRoot(4);
			padre = T.root();
			hijo = T.addFirstChild(padre, 5);
		} catch (InvalidPositionException e1) {
			fail("addFirstChild() no debería lanzar la excepción InvalidPositionException para una posición válida");
		} catch (InvalidOperationException e) {

			fail("addFirstChild() no debería lanzar una excepción InvalidOperationException para un árbol vacío");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar una excepcion EmptyTreeException en un arbol no vacío");
		}

		try {
			T = getTree();
			T.addAfter(padre, hijo, 6);
			fail("addAfter() debería lanzar la excepcion InvalidPositionException cuando el árbol esta vacío");
		} catch (InvalidPositionException e1) {
		}

		try {
			T = getTree();
			T.createRoot(3);
			padre = T.root();
			T = getTree();
			T.createRoot(1);
			h1 = T.addFirstChild(T.root(), 2);
		} catch (InvalidPositionException e1) {
			fail("addFirstChild() no debería lanzar la excepción InvalidPositionException para una posición válida");
		} catch (InvalidOperationException e) {

			fail("addFirstChild() no debería lanzar una excepción InvalidOperationException para un árbol vacío");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar una excepcion EmptyTreeException en un arbol no vacío");
		}
		try {
			T.addAfter(padre, h1, 4);
			fail("addAfter() debería lanzar la excepcion InvalidPositionException cuando el nodo primer argumento no es el padre del nodo segundo argumento");
		} catch (InvalidPositionException e1) {

		}
		try {
			T = getTree();
			T.createRoot(1);
			h5 = T.addFirstChild(T.root(), 2);
			h1 = T.addAfter(T.root(), h5, 3);
			// prueba para 3 como primer hijo de root
			Iterator<Position<Integer>> it = T.children(T.root()).iterator();
			assertTrue("addAfter() no funciona correctamente", it.next()
					.element() == 2);
			assertTrue("addAfter() no funciona correctamente", it.next()
					.element() == 3);
			h2 = T.addFirstChild(h1, 4);
			h3 = T.addAfter(T.root(), h1, 6);
			T.addAfter(T.root(), h5, 7);
			T.addAfter(T.root(), h3, 9);
			T.addAfter(h1, h2, 5);
			chequearPadre(T.root());
			// caso de prueba para los hijos de root
			PositionList<Integer> hijosRoot = new DoubleLinkedList<Integer>();
			hijosRoot.addFirst(2);
			hijosRoot.addFirst(7);
			hijosRoot.addFirst(3);
			hijosRoot.addFirst(6);
			hijosRoot.addFirst(9);
			PositionList<Integer> hijos = new DoubleLinkedList<Integer>();
			for (Position<Integer> n : T.children(T.root())) {
				hijos.addFirst(n.element());
			}
			assertTrue("addAfter() no funciona correctamente",
					hijos.size() == hijosRoot.size());
			while (!hijos.isEmpty()) {
				assertTrue("addAfter() no funciona correctamente", hijos
						.first().element().equals(hijosRoot.first().element()));
				hijos.remove(hijos.first());
				hijosRoot.remove(hijosRoot.first());

			}
			// caso de prueba para los hijos de h1
			hijosH1 = new DoubleLinkedList<Integer>();
			hijosH1.addFirst(5);
			hijosH1.addFirst(4);
			hjsH1 = new DoubleLinkedList<Integer>();
			for (Position<Integer> n : T.children(h1)) {
				hjsH1.addLast(n.element());
			}
			assertTrue("addAfter() no funciona correctamente",
					hjsH1.size() == hijosH1.size());
			while (!hjsH1.isEmpty()) {
				assertTrue("addAfter() no funciona correctamente", hjsH1
						.first().element().equals(hijosH1.first().element()));
				hjsH1.remove(hjsH1.first());
				hijosH1.remove(hijosH1.first());
			}
		} catch (InvalidPositionException e1) {
			fail("addAfter(), children(), remove() o addFirstChild() no debería lanzar InvalidPositionException con una posición válida");
		} catch (EmptyTreeException e1) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío");
		} catch (InvalidOperationException e1) {
			fail("createRoot() no debería lanzar la excepción InvalidOperationException para un árbol vacío.");
		} catch (EmptyListException e1){fail("los métodos first() o last() no debería lanzar la excepción EmptyListException para una Lista con elementos [´PROBLEMA EN TDALISTA]");}

	}

	@Test
	public void removeExternal() {
		Position<Integer> h1, h3;
		Position<Integer> nodo;
		Iterator<Position<Integer>> hijos;
		try {

			T.createRoot(1);
			nodo = T.root();
			T = getTree();
			T.removeExternalNode(nodo);
			fail("removeExternalNode() debería lanzar una excepción InvalidPositionException cuando el árbol esta vacio");
		} catch (InvalidPositionException e) {

		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar InvaliOperationException en un árbol vacío");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar EmptyTreeExceptionen un árbol no vacío");
		}
		try {
			T = getTree();
			T.createRoot(1);
			T.addFirstChild(T.root(), 2);

		} catch (InvalidPositionException e) {
			fail("addFirstChild() no debería lanzar la excepción InvalidPositionException con una posición válida.");
		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar InvaliOperationException en un árbol vacío");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar EmptyTreeException en un árbol no vacío");
		}
		try {
			T.removeExternalNode(T.root());
			fail("removeExternalNode() debería lanzar una excepción InvalidPositionException cuando su argumento es un nodo interno ");
		} catch (InvalidPositionException e) {
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar EmptyTreeException en un árbol no vacío");
		}
		try {
			T = getTree();
			T.createRoot(1);
			T.removeExternalNode(T.root());
		} catch (InvalidPositionException e) {
			fail("removeExternalNode() no debería lanzar un excepción InvalidPositionException cuando el argumento es la raiz sin hijos");
		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar InvaliOperationException en un árbol vacío");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar EmptyTreeException en un árbol no vacío");
		}
		try {

			T = getTree();
			T.createRoot(1);
			h1 = T.addFirstChild(T.root(), 2);
			T.addFirstChild(T.root(), 3);
			h3 = T.addFirstChild(T.root(), 4);
			T.removeExternalNode(h1);
			// caso de prueba para remover el primer hijo de tres siendo este un
			// nodo externo
			assertTrue("removeExternal() no funciona correctamente", T
					.children(T.root()).iterator().next().element() == 4);
			T.removeExternalNode(h3);
			// caso de prueba para remover el segundo hijo de dos siendo este un
			// nodo externo
			hijos = T.children(T.root()).iterator();
			assertTrue("removeExternal() no funciona correctamente", hijos
					.next().element() == 3);
			assertTrue("removeExternal() no funciona correctamente",
					hijos.hasNext() == false);
		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar InvaliOperationException en un árbol vacío");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar EmptyTreeException en un árbol no vacío");
		} catch (InvalidPositionException e) {
			fail("addFirstChild(), removeExternalNode() o children() no debería lanzar InvalidPositionException al recibir como argumento la raiz");
		}
	}

	@Test
	public void removeInternal() {
		Position<Integer> nodo, pos;
		PositionList<Integer> lista;
		Position<Integer> p, p1, p2;
		Iterator<Position<Integer>> it;
		boolean paso = false;
		int i = 0;
		// Arbol Vacío
		try {
			T.createRoot(1);
			nodo = T.root();
			T = getTree();
			T.removeInternalNode(nodo);
			fail("removeInternalNode() debería lanzar una excepción InvalidPositionException cuando el árbol esta vacio");
		} catch (InvalidPositionException e) {
		} catch (InvalidOperationException e) {
			fail("root() no debería lanzar InvaliOperationException en un árbol no vacío");
		} catch (EmptyTreeException e) {
			fail("createRoot() no debería lanzar InvaliOperationException en un árbol vacío");
		}
		// Raiz con hijos
		try {
			cargarArbol(T);
			i = T.size();
			T.removeInternalNode(T.root());
			fail("removeInternalNode() debería lanzar una excepción InvalidPositionException cuando su argumento es la raiz con hijos ");

		} catch (InvalidPositionException e) {
			assertTrue("RemoveInternal no funciona correctamente",
					T.size() == i);
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar InvaliOperationException en un árbol no vacío");
		}
		//Raíz con 1 solo hijo
		T = getTree();
		cargarArbol3(T);
		Position<Integer> raiz=null, hijo=null;
		try{
		raiz=T.root();
		hijo= T.children(T.root()).iterator().next();
		}catch (EmptyTreeException e){fail("children() debería lanzar una excepción EmptyTreeException cuando el árbol no está vacío");}
		 catch (InvalidPositionException e){fail("children() no debería lanzar una excepción InvalidPositionException para una posición válida");}
		try{
			T.removeInternalNode(raiz);
			raiz=T.parent(hijo);
			fail("El método parent debería haber lanzado la excepción BoundaryViolationException al intentar eliminar la raíz del árbol.");
		}catch(InvalidPositionException e){fail("El método removeInternalNode no debería lanzar esta excepción al intentar eliminar la raíz de un árbol cuando esta tiene un sólo hijo.");
	    }catch(BoundaryViolationException e){}
		// Raiz sin hijos.
		try {
			T = getTree();
			T.createRoot(1);
			T.removeInternalNode(T.root());
			fail("removeInternalNode() debería lanzar una excepción InvalidPositionException cuando su argumento es un nodo externo ");

		} catch (InvalidPositionException e) {
		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar InvaliOperationException en un árbol vacío");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar InvaliOperationException en un árbol no vacío");
		}
		// Nodo interno.
		T = getTree();
		cargarArbol2(T);
		// Intento eliminar la raiz
		try {
			T.removeInternalNode(T.root());
			fail("removeInternal debería lanzar la excepción InvalidPositionException al invocarlo con la raíz con hijos.");
		} catch (InvalidPositionException e) {
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío.");
		}
		// Intento eliminar un nodo externo
		try {
			it = T.children(T.root()).iterator();
			paso = true;
			T.removeInternalNode(it.next());
			fail("removeInternalNode() debería haber lanzado la excepción InvalidPositionException");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío.");
		} catch (InvalidPositionException e) {
			if (!paso)
				fail("children() no debería lanzar la excepción InvalidPositionException para una posición válida.");
		}
		// Intento eliminar nodos internos.
		lista = new DoubleLinkedList<Integer>();
		for (i = 1; i <= 12; i++)
			lista.addLast(i);
		chequearHijosNiveles2(lista);
		try {
			it = T.positions().iterator();
			pos = it.next();
			while ((it.hasNext()) && (!pos.element().equals(5)))
				pos = it.next();
			T.removeInternalNode(pos);
			chequearPadre(T.root());
			lista = new DoubleLinkedList<Integer>();
			for (i = 1; i <= 4; i++)
				lista.addLast(i);
			p1 = lista.last();
			lista.addLast(9);
			lista.addLast(10);
			p = lista.last();
			for (i = 6; i <= 8; i++)
				lista.addLast(i);
			p2 = lista.last();
			lista.addLast(12);
			lista.addLast(11);
			chequearHijosNiveles2(lista);
			// elimino el nodo 12
			lista.set(p, 12);
			lista.remove(lista.prev(lista.last()));
			it = T.positions().iterator();
			pos = it.next();
			while ((it.hasNext()) && (!pos.element().equals(10)))
				pos = it.next();
			T.removeInternalNode(pos);
			chequearPadre(T.root());
			chequearHijosNiveles2(lista);
			// elimino el nodo 4
			lista.set(p1, 8);
			lista.set(p2, 11);
			lista.remove(lista.last());
			it = T.positions().iterator();
			pos = it.next();
			while ((it.hasNext()) && (!pos.element().equals(4)))
				pos = it.next();
			T.removeInternalNode(pos);
			chequearPadre(T.root());
			chequearHijosNiveles2(lista);
			// elimino el nodo externo 2
			it = T.positions().iterator();
			pos = it.next();
			while ((it.hasNext()) && (!pos.element().equals(2)))
				pos = it.next();
			T.removeExternalNode(pos);
			it = T.children(T.root()).iterator();
			pos = it.next();
			assertTrue("error al eliminar nodo externo", pos.element()
					.equals(3));
			T.removeInternalNode(pos);
			chequearPadre(T.root());
			lista = new DoubleLinkedList<Integer>();
			lista.addLast(1);
			lista.addLast(6);
			lista.addLast(7);
			lista.addLast(8);
			lista.addLast(9);
			lista.addLast(12);
			lista.addLast(11);
			chequearHijosNiveles2(lista);
		} catch (InvalidPositionException e) {
			fail("removeInternalNode(), children(), removeExternalNode(), remove(), o set() no debería lanzar la excepción InvalidPositionException para una posición válida.");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar la excepción EmptyTreeException para un árbol no vacío.");
		} catch (BoundaryViolationException e) {
			fail("prev() no debería lanzar la excepción BoundaryViolationException con una posición .");
		} catch (EmptyListException e1){fail("los métodos first() o last() no debería lanzar la excepción EmptyListException para una Lista con elementos [´PROBLEMA EN TDALISTA]");}

	}

	@Test
	public void remove() {
		// Arbol Vacío
		PositionList<Position<Integer>> lista;
		Position<Integer> pos, nodo;
		Iterator<Position<Integer>> it;
		int i = 0;
		try {
			T.createRoot(1);
			nodo = T.root();
			T = getTree();
			T.removeNode(nodo);
			fail("removeNode() debería lanzar una excepción InvalidPositionException cuando el árbol esta vacio");
		} catch (InvalidPositionException e) {
		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar InvaliOperationException en un árbol vacío");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar InvaliOperationException en un árbol no vacío");
		}
		// Raiz con hijos
		try {
			cargarArbol(T);
			i = T.size();
			T.removeNode(T.root());
			fail("removeNode() debería lanzar una excepción InvalidPositionException cuando su argumento es la raiz con hijos ");

		} catch (InvalidPositionException e) {
			assertTrue("removeNode no funciona correctamente", T.size() == i);
		} catch (EmptyTreeException e) {
			fail("createRoot() no debería lanzar InvaliOperationException en un árbol no vacío");
		}
		// Raiz sin hijos.
		try {
			T = getTree();
			T.createRoot(1);
			T.removeNode(T.root());
			assertTrue("removeNode no funciona correctamente", T.size() == 0);

		} catch (InvalidPositionException e) {
			fail("removeNode() no debería lanzar un excepción InvalidPositionException cuando el argumento es la raiz sin hijos");
		} catch (InvalidOperationException e) {
			fail("createRoot() no debería lanzar InvaliOperationException en un árbol vacío");
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar InvaliOperationException en un árbol no vacío");
		}
		// Elimino todos los nodos del nivel 1

		T = getTree();
		cargarArbol2(T);
		lista = new DoubleLinkedList<Position<Integer>>();
		try {
			nodosEnNivel(T.root(), 1, 1, lista);
			it = lista.iterator();

			while (it.hasNext())
				T.removeNode(it.next());
			chequearPadre(T.root());
			it = T.children(T.root()).iterator();
			for (i = 6; i < 11; i++) {
				pos = it.next();
				assertTrue("remove() no funciona correctamente", pos.element()
						.equals(i));
				assertTrue("remove() no setea correctamente el padre",
						T.parent(pos) == T.root());
			}
			assertTrue("remove() no funciona correctamente", !it.hasNext());
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar EmptyTreeException para un árbol no vacío.");
		} catch (InvalidPositionException e) {
			fail("removeNode(), children o parent() no debería lanzar InvalidPositionException para una posición válida.");
		} catch (BoundaryViolationException e) {
			fail("parent() no debería lanzar BoundaryViolationException para una posición distinta a la raíz.");
		}
		// Elimino todos los nodos del nivel 1
		try {
			lista = new DoubleLinkedList<Position<Integer>>();
			nodosEnNivel(T.root(), 1, 1, lista);
			it = lista.iterator();
			while (it.hasNext())
				T.removeNode(it.next());
			chequearPadre(T.root());
			it = T.children(T.root()).iterator();
			for (i = 11; i < 13; i++) {
				pos = it.next();
				assertTrue("remove() no funciona correctamente", pos.element()
						.equals(i));
				assertTrue("remove() no setea correctamente el padre",
						T.parent(pos) == T.root());
			}
			assertTrue("remove() no funciona correctamente", !it.hasNext());
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar EmptyTreeException para un árbol no vacío.");
		} catch (InvalidPositionException e) {
			fail("removeNode(),children o parent() no debería lanzar InvalidPositionException para una posición válida.");
		} catch (BoundaryViolationException e) {
			fail("parent() no debería lanzar BoundaryViolationException para una posición distinta a la raíz.");
		}

		try {
			lista = new DoubleLinkedList<Position<Integer>>();
			nodosEnNivel(T.root(), 1, 1, lista);
			it = lista.iterator();
			while (it.hasNext())
				T.removeNode(it.next());
			chequearPadre(T.root());
			it = T.children(T.root()).iterator();
			assertTrue("remove() no funciona correctamente", !it.hasNext());
			assertTrue("remove() no funciona correctamente", T.size() == 1);
		} catch (EmptyTreeException e) {
			fail("root() no debería lanzar EmptyTreeException para un árbol no vacío.");
		} catch (InvalidPositionException e) {
			fail("removeNode(), children o parent() no debería lanzar InvalidPositionException para una posición válida.");
		}

	}

	private void cargarArbol(Tree<Integer> Arbol) {
		Position<Integer> h1, h2, h3, h4, h5;
		h1 = h2 = h3 = h4 = h5 = null;
		try {
			Arbol.createRoot(1);
			h1 = Arbol.addFirstChild(Arbol.root(), 2);
			h1 = Arbol.addAfter(Arbol.root(), h1, 3);
			h2 = Arbol.addAfter(Arbol.root(), h1, 6);
			h3 = Arbol.addAfter(Arbol.root(), h2, 9);
			Arbol.addFirstChild(h1, 4);
			Arbol.addLastChild(h1, 5);
			h4 = Arbol.addFirstChild(h2, 7);
			Arbol.addFirstChild(h4, 8);
			Arbol.addFirstChild(h3, 10);
			h5 = Arbol.addLastChild(h3, 11);
			Arbol.addFirstChild(h5, 12);

		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(), addLastChild() o addAfter() con una posición válida lanza la excepción InvalidPositionException");
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raíz de un árbol no vacío lanza la excepción EmptyTreeException");
		} catch (InvalidOperationException e3) {
			fail("Al invocar createRoot() con un árbol vacío lanza la excepción InvalidOperationException");
		}

	}	
	
	private void cargarArbol3(Tree<Integer> Arbol) {
		Position<Integer> h1;
		h1 = null;
		try {
			Arbol.createRoot(1);
			h1 = Arbol.addFirstChild(Arbol.root(), 2);
			Arbol.addFirstChild(h1, 6);
		
		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(), addLastChild() o addAfter() con una posición válida lanza la excepción InvalidPositionException");
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raíz de un árbol no vacío lanza la excepción EmptyTreeException");
		} catch (InvalidOperationException e3) {
			fail("Al invocar createRoot() con un árbol vacío lanza la excepción InvalidOperationException");
		}

	}

	private void cargarArbol2(Tree<Integer> Arbol) {
		Position<Integer> h1, h2, h3, h4, h5;
		h1 = h2 = h3 = h4 = h5 = null;
		try {
			Arbol.createRoot(1);
			h1 = Arbol.addFirstChild(Arbol.root(), 2);
			h1 = Arbol.addAfter(Arbol.root(), h1, 3);
			h2 = Arbol.addAfter(Arbol.root(), h1, 4);
			h3 = Arbol.addAfter(Arbol.root(), h2, 5);
			Arbol.addFirstChild(h1, 6);
			Arbol.addLastChild(h1, 7);
			h4 = Arbol.addFirstChild(h2, 8);
			Arbol.addFirstChild(h4, 11);
			Arbol.addFirstChild(h3, 9);
			h5 = Arbol.addLastChild(h3, 10);
			Arbol.addFirstChild(h5, 12);

		} catch (InvalidPositionException e) {
			fail("Al invocar addFirstChild(), addLastChild() o addAfter() con una posición válida lanza la excepción InvalidPositionException");
		} catch (EmptyTreeException e2) {
			fail("Al solicitar la raíz de un árbol no vacío lanza la excepción EmptyTreeException");
		} catch (InvalidOperationException e3) {
			fail("Al invocar createRoot() con un árbol vacío lanza la excepción InvalidOperationException");
		}

	}

	private void nodosEnNivel(Position<Integer> v, int nivel, int j,
			PositionList<Position<Integer>> lista) {
		Position<Integer> p;
		Iterator<Position<Integer>> hijos = null;
		try {
			hijos = T.children(v).iterator();
		} catch (InvalidPositionException e) {
			fail("childen() no debería lanzar InvalidPositionException con una posición válida.");
		}

		while (hijos.hasNext()) {
			p = hijos.next();
			if (nivel == j)
				lista.addLast(p);

			nodosEnNivel(p, nivel + 1, j, lista);
		}
	}

	private void chequearPadre(Position<Integer> v) {
		Position<Integer> p;
		Iterator<Position<Integer>> hijos = null;
		try {
			hijos = T.children(v).iterator();
		} catch (InvalidPositionException e) {
			fail("childen() no debería lanzar InvalidPositionException con una posición válida.");
		}

		while (hijos.hasNext()) {
			p = hijos.next();
			try {
				assertTrue("parent() no funciona correctamente",
						T.parent(p) == v);
			} catch (InvalidPositionException e) {
				fail("parent() no debería lanzar InvalidPositionException con una posición válida.");
			} catch (BoundaryViolationException e) {
				fail("parent() no debería lanzar BoundaryViolationException con una posición distinta a la de la raíz.");
			}
			chequearPadre(p);
		}
	}

	private void chequearHijosNiveles2(PositionList<Integer> lista) {
		Position<Integer> p;
		int el = 0;
		Iterator<Integer> it = lista.iterator();
		Queue<Position<Integer>> cola = new ArrayQueue<Position<Integer>>();
		Iterator<Position<Integer>> hijos = null;
		try {
			p = T.root();

		} catch (EmptyTreeException e1) {
			fail("root() no debería lanzar la excepción EmptyTreeException con un árbol con elementos.");
			p = null;
		}
		if (p != null) {
			cola.enqueue(p);
			cola.enqueue(null);
			while (!cola.isEmpty()) {
				try {
					p = cola.dequeue();
				} catch (EmptyQueueException e) {
					fail("dequeue() no debería lanzar EmptyQueueException para una cola con elementos.");
				}
				if (p == null) {
					if (!cola.isEmpty())
						cola.enqueue(null);
				} else {
					el = it.next();
					assertTrue("error", p.element().equals(el));

					try {
						hijos = T.children(p).iterator();
					} catch (InvalidPositionException e) {
						fail("children() no debería lanzar la excepción InvalidPositionException para una posición válida");
					}
					while (hijos.hasNext()) {
						cola.enqueue(hijos.next());

					}
				}

			}

		}
	}

}
