package TDAColaConPrioridad;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {
	private PriorityQueue<Integer, String> s; // interface
	private String n1, n2, n3, n4, n5, n6;
	private Integer i1, i2, i3, i4, i5, i6;

	private PriorityQueue<Integer, String> getPriorityQueue() {
		return new ArrayHeapPQueue<Integer, String>(10000,new Comparator<Integer>());

	}

	@Before
	public void setUp() {
		s = getPriorityQueue();
		n1 = "Uno";
		n2 = "Dos";
		n3 = "Tres";
		n4 = "Cuatro";
		n5 = "Cinco";
		n6 = "Seis";
		i1 = 1;
		i2 = 2;
		i3 = 3;
		i4 = 4;
		i5 = 5;
		i6 = 6;

	}
/*_______________________TESTEAMOS EL METODO size()_____________________________*/
	@Test
	public void size() {// Pruebo si insertar() actualiza el size()
		try {
			assertTrue(
					"Tama�o de la cola con prioridad justo despu�s de ser creada != 0",
					s.size() == 0);
			s.insert(i1, n1);
			assertTrue(
					"Tama�o de la cola con prioridad luego de insertar una vez != 1",
					s.size() == 1);
			s.insert(i2, n2);
			assertTrue(
					"Tama�o de la cola con prioridad luego de insertar dos veces != 2",
					s.size() == 2);
			s.insert(i3, n3);
			assertTrue(
					"Tama�o de la cola con prioridad luego de insertar tres veces != 3",
					s.size() == 3);
		} catch (InvalidKeyException e) {
			fail("El m�todo insert() no deber�a lanzar InvalidkeyException con una clave v�lida.");
		}

		// Pruebo si min() y removeMin() actualizan el size()
		try {
			s.min();
			assertTrue(
					"Tama�o de la cola con prioridad cambio despu�s de invocar min()",
					s.size() == 3);
			s.removeMin();
			assertTrue(
					"Tama�o de la cola con prioridad luego de eliminar el m�nimo es != 2",
					s.size() == 2);
			s.min();
			assertTrue(
					"Tama�o de la cola con prioridad cambio despu�s de invocar min()",
					s.size() == 2);
			s.removeMin();
			assertTrue(
					"Tama�o de la cola con prioridad luego de eliminar el m�nimo es != 2",
					s.size() == 1);
			s.min();
			assertTrue(
					"Tama�o de la cola con prioridad cambio despu�s de invocar min()",
					s.size() == 1);
			s.removeMin();
			assertTrue(
					"Tama�o de la cola con prioridad luego de eliminar el m�nimo es != 2",
					s.size() == 0);
		} catch (EmptyPriorityQueueException e) {
			e.printStackTrace();
			fail("El m�todo min() o removeMin() no deber�a lanzar EmptyQueueException con una cola con elementos.");
		}

	}
	
/*_______________________TESTEAMOS EL METODO isEmpty()_____________________________*/
	@Test
	public void isEmpty() {// Pruebo al insertar(), isEmpty() funciona
		// correctamente
		try {
			assertTrue(
					"La cola con prioridad no est� vac�a justo despu�s de ser creada",
					s.isEmpty());
			s.insert(i1, n1);
			assertTrue(
					"La cola con prioridad est� vac�a justo despu�s de insertar 1 elemento",
					!s.isEmpty());
			s.insert(i2, n2);
			assertTrue(
					"La cola con prioridad est� vac�a justo despu�s de insertar 2 elementos",
					!s.isEmpty());
			s.insert(i3, n3);
			assertTrue(
					"La cola con prioridad est� vac�a justo despu�s de insertar 3 elementos",
					!s.isEmpty());
		} catch (InvalidKeyException e) {
			fail("El m�todo insert() no deber�a lanzar InvalidkeyException con una clave v�lida.");
		}
		// Pruebo si al invocar min() y removeMin(), isEmpty() funciona
		// correctamente
		try {
			s.min();
			assertTrue(
					"La cola con prioridad est� vac�a justo despu�s de insertar invocar min() teniendo 3 elementos",
					!s.isEmpty());
			s.removeMin();
			assertTrue(
					"La cola con prioridad est� vac�a justo despu�s de eliminar 1 elemento, quedando 2.",
					!s.isEmpty());
			s.min();
			assertTrue(
					"La cola con prioridad est� vac�a justo despu�s de insertar invocar min() teniendo 2 elementos",
					!s.isEmpty());
			s.removeMin();
			assertTrue(
					"La cola con prioridad est� vac�a justo despu�s de eliminar 1 elemento, quedando 1.",
					!s.isEmpty());
			s.min();
			assertTrue(
					"La cola con prioridad est� vac�a justo despu�s de insertar invocar min() teniendo 1 elemento",
					!s.isEmpty());
			s.removeMin();
			assertTrue(
					"La cola con prioridad no est� vac�a justo despu�s de eliminar todos los elementos que ten�a.",
					s.isEmpty());
		} catch (EmptyPriorityQueueException e) {
			e.printStackTrace();
			fail("El m�todo min() o removeMin() no deber�a lanzar EmptyQueueException con una cola con elementos.");
		}

	}
	
/*_______________________TESTEAMOS EL METODO min()_____________________________*/
	@Test
	public void min() {
		Entry<Integer, String> entrada;
		// Cola vac�a
		try {
			entrada = s.min();
			fail("min() deber�a lanzar la excepci�n EmptyPriorityQueue para una cola vac�a.");

		} catch (EmptyPriorityQueueException e) {
		}
		// Insertando 6 elementos fuera de orden.
		try {
			s.insert(i4, n4);
			s.insert(i1, n1);
			s.insert(i5, n5);
			s.insert(i3, n3);
			s.insert(i6, n6);
			s.insert(i2, n2);

		} catch (InvalidKeyException e) {
			fail("El m�todo insert() no deber�a lanzar InvalidkeyException con una clave v�lida.");
		}
		// Tengo que sacar los 6 elementos en orden.
		try {
			entrada = s.min();
			assertTrue("Min() no funciona correctamente", (entrada.getKey()
					.equals(i1) && entrada.getValue().equals(n1)));
			s.removeMin();
			entrada = s.min();
			assertTrue("Min() no funciona correctamente", (entrada.getKey()
					.equals(i2) && entrada.getValue().equals(n2)));
			s.removeMin();
			entrada = s.min();
			assertTrue("Min() no funciona correctamente", (entrada.getKey()
					.equals(i3) && entrada.getValue().equals(n3)));
			s.removeMin();
			entrada = s.min();
			assertTrue("Min() no funciona correctamente", (entrada.getKey()
					.equals(i4) && entrada.getValue().equals(n4)));
			s.removeMin();
			entrada = s.min();
			assertTrue("Min() no funciona correctamente", (entrada.getKey()
					.equals(i5) && entrada.getValue().equals(n5)));
			s.removeMin();
			entrada = s.min();
			assertTrue("Min() no funciona correctamente", (entrada.getKey()
					.equals(i6) && entrada.getValue().equals(n6)));
			s.removeMin();

		} catch (EmptyPriorityQueueException e) {
			e.printStackTrace();
			fail("El m�todo min() o removeMin() no deber�a lanzar EmptyQueueException con una cola con elementos.");
		}

		// Cola vac�a
		try {
			entrada = s.min();
			fail("min() deber�a lanzar la excepci�n EmptyPriorityQueue para una cola vac�a.");

		} catch (EmptyPriorityQueueException e) {
		}
		// Insertando 1000 elementos fuera de orden.
		try {
			for (int i = 40; i <= 50; i++)
				s.insert(i, n1);
			for (int i = 60; i >= 51; i--)
				s.insert(i, n2);
			for (int i = 61; i <= 75; i++)
				s.insert(i, n3);
			for (int i = 30; i <= 39; i++)
				s.insert(i, n4);
			for (int i = 1000; i > 100; i--)
				s.insert(i, n5);
			for (int i = 29; i > 0; i--)
				s.insert(i, n5);
			for (int i = 90; i <= 100; i++)
				s.insert(i, n6);
			for (int i = 89; i > 75; i--)
				s.insert(i, n5);
		} catch (InvalidKeyException e) {
			fail("El m�todo insert() no deber�a lanzar InvalidkeyException con una clave v�lida.");
		}

		// Tengo que sacar los 1000 elementos en orden.
		try {
			for (int i = 1; i <= 1000; i++) {
				entrada = s.min();
				assertTrue("Min() no funciona correctamente", entrada.getKey()
						.equals(i));
				s.removeMin();
			}
		} catch (EmptyPriorityQueueException e) {
			e.printStackTrace();
			fail("El m�todo min() o removeMin() no deber�a lanzar EmptyQueueException con una cola con elementos.");
		}

		try {
			entrada = s.min();
			fail("min() deber�a lanzar la excepci�n EmptyPriorityQueue para una cola vac�a.");

		} catch (EmptyPriorityQueueException e) {
		}
	}
	
/*_______________________TESTEAMOS LOS METODOS removeMin() e insert()_____________________________*/
	@Test
	public void insert_removeMin() {
		Entry<Integer, String> entrada;
		// Cola vac�a
		try {
			entrada = s.removeMin();
			fail("removeMin() deber�a lanzar la excepci�n EmptyPriorityQueue para una cola vac�a.");
		} catch (EmptyPriorityQueueException e) {
		}
		// Insertando 6 elementos fuera de orden.
		try {
			s.insert(i4, n4);
			s.insert(i1, n1);
			s.insert(i5, n5);
			s.insert(i3, n3);
			s.insert(i6, n6);
			s.insert(i2, n2);

		} catch (InvalidKeyException e) {
			fail("El m�todo insert() no deber�a lanzar InvalidkeyException con una clave v�lida.");
		}
		// insertando con una clave inv�lida.
		try {
			s.insert(null, n1);
			fail("insert() deber�a lanzar la excepci�n InvalidKeyException para una clave nula.");
		} catch (InvalidKeyException e) {
		}

		// Tengo que sacar los 6 elementos en orden.
		try {
			entrada = s.removeMin();
			assertTrue("removeMin() no funciona correctamente", (entrada
					.getKey().equals(i1) && entrada.getValue().equals(n1)));
			entrada = s.removeMin();
			assertTrue("removeMin() no funciona correctamente", (entrada
					.getKey().equals(i2) && entrada.getValue().equals(n2)));
			entrada = s.removeMin();
			assertTrue("removeMin() no funciona correctamente", (entrada
					.getKey().equals(i3) && entrada.getValue().equals(n3)));
			entrada = s.removeMin();
			assertTrue("removeMin() no funciona correctamente", (entrada
					.getKey().equals(i4) && entrada.getValue().equals(n4)));
			entrada = s.removeMin();
			assertTrue("removeMin() no funciona correctamente", (entrada
					.getKey().equals(i5) && entrada.getValue().equals(n5)));
			entrada = s.removeMin();
			assertTrue("removeMin() no funciona correctamente", (entrada
					.getKey().equals(i6) && entrada.getValue().equals(n6)));

		} catch (EmptyPriorityQueueException e) {
			fail("El m�todo removeMin() no deber�a lanzar EmptyQueueException con una cola con elementos.");
		}

		// Cola vac�a.
		try {
			entrada = s.removeMin();
			fail("removeMin() deber�a lanzar la excepci�n EmptyPriorityQueue para una cola vac�a.");

		} catch (EmptyPriorityQueueException e) {
		}
		// Insertando 1000 elementos fuera de orden.
		try {
			for (int i = 40; i <= 50; i++)
				s.insert(i, n1);
			for (int i = 60; i >= 51; i--)
				s.insert(i, n2);
			for (int i = 61; i <= 75; i++)
				s.insert(i, n3);
			for (int i = 30; i <= 39; i++)
				s.insert(i, n4);
			for (int i = 29; i > 0; i--)
				s.insert(i, n5);
			for (int i = 1000; i > 100; i--)
				s.insert(i, n5);
			for (int i = 90; i <= 100; i++)
				s.insert(i, n6);
			for (int i = 89; i > 75; i--)
				s.insert(i, n5);
		} catch (InvalidKeyException e) {
			fail("El m�todo insert() no deber�a lanzar InvalidkeyException con una clave v�lida.");
		}
		// Tengo que sacar los 1000 elementos en orden.
		try {
			for (int i = 1; i <= 1000; i++) {
				entrada = s.removeMin();
				assertTrue("removeMin() no funciona correctamente", entrada
						.getKey().equals(i));
			}
		} catch (EmptyPriorityQueueException e) {
			fail("El m�todo removeMin() no deber�a lanzar EmptyQueueException con una cola con elementos.");
		}
		// Cola vac�a.
		try {
			entrada = s.removeMin();
			fail("removeMin() deber�a lanzar la excepci�n EmptyPriorityQueue para una cola vac�a.");

		} catch (EmptyPriorityQueueException e) {
		}
	}

}
