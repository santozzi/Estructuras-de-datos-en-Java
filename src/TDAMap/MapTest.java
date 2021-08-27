/**
 * Class: MapTest
 * @author María Luján Ganuza (mlg@cs.uns.edu.ar)
 *         Estructuras de Datos _ Primer Cuatrimestre 2013
 *         Departamento de Cs. e Ing. de la Computación.
 *  @version: 2.0
 */

package TDAMap;

import static org.junit.Assert.*;

import java.util.Random;


import java.util.*;

import org.junit.Before;
import org.junit.Test;


public class MapTest {

	private Map<Integer, Integer> s; // interface

	private Integer n1, n2, n3;

	private Integer i1, i2, i3;

	private Map<Integer, Integer> getMapeo() {

		return new OpenHashMap<Integer, Integer>();

	}

	@Before
	public void setUp() {

		s = getMapeo();

		i1 = 66554;

		i2 = 32523;

		i3 = 34230;

		n1 = 6;

		n2 = 8;

		n3 = 3;
				
	}

	/* TESTEANDO EL METODO SIZE() */

	@Test
	public void size() {

		// Testeando si put() actualiza el size correctamente.

		assertTrue("Tamaño de Mapeo justo después de ser creada != 0",
				s.size() == 0);
		try {
			s.put(i1, n1);

			assertTrue("Tamaño de Mapeo luego de insertar una vez != 1",
					s.size() == 1);

			s.put(i2, n2);

			assertTrue("Tamaño de Mapeo luego de insertar dos veces != 2",
					s.size() == 2);

			s.put(i3, n3);

			assertTrue("Tamaño de Mapeo luego de insertar tres veces != 3",
					s.size() == 3);

			// Testeando que el método remove() actualice el size, y que get(K key) no lo haga.

			s.get(i1);

			assertTrue("Tamaño de Mapeo cambió al invocar get()",
					s.size() == 3);

			s.remove(i1);

			assertTrue(
					"Tamaño de Mapeo luego de eliminar una entrada es != 2",
					s.size() == 2);

			s.get(i2);

			assertTrue("Tamaño de Mapeo cambió al invocar get()",
					s.size() == 2);

			s.remove(i2);

			assertTrue("Tamaño de Mapeo luego de eliminar es != 1",
					s.size() == 1);

			s.get(i3);

			assertTrue("Tamaño de Mapeo cambió al invocar get()",
					s.size() == 1);

			s.remove(i3);

			assertTrue("Tamaño de Mapeo luego de eliminar es != 0",
					s.size() == 0);

		} catch (InvalidKeyException e) {
			fail("Los métodos remove() o get() no deberían lanzar excepción para una clave válida.");
		}
	}

	/* TESTEANDO EL METODO isEmpty() */

	@Test
	public void isEmpty() {
		
	  try {

			assertTrue("EL Mapeo no está vacío justo después de ser creado",
					s.isEmpty());

			s.put(i1, n1);

			assertTrue("El Mapeo está vacía después de insertar 1 elemento",
					!s.isEmpty());

			s.put(i2, n2);

			assertTrue("El Mapeo está vacía después de insertar 2 elementos",
					!s.isEmpty());

			s.put(i3, n3);

			assertTrue("El Mapeo está vacía después de insertar 3 elementos",
					!s.isEmpty());

		} catch (InvalidKeyException e) {

			fail("El método put() no debería lanzar InvalidkeyException con una clave válida.");
		}
		
		try {

			s.get(i1);

			assertTrue(
					"El Mapeo está vacío después de invocar get() teniendo 3 elementos",
					!s.isEmpty());

			s.remove(i1);

			assertTrue(
					"El Mapeo está vacío después de eliminar 1 elemento, quedando 2.",
					!s.isEmpty());

			s.get(i2);

			assertTrue(
					"El Mapeo está vacío después de invocar get(i2) teniendo 2 elementos",
					!s.isEmpty());

			s.remove(i2);

			assertTrue(
					"El Mapeo está vacío después de eliminar 1 elemento, quedando 1.",
					!s.isEmpty());

			s.get(i3);

			assertTrue(
					"El Mapeo está vacío después de invocar get(i3) teniendo 1 elemento",
					!s.isEmpty());

			s.remove(i3);

			assertTrue(
					"EL Mapeo no está vacío después de eliminar todos los elementos que tenía.",
					s.isEmpty());

		} catch (InvalidKeyException e) {

			fail("El método get(K key) o remove(K key) no debería lanzar InvalidKeyException con una clave válida.");

		}

	}

	/* TESTEANDO EL METODO get(K key) */

	@Test
	public void get() {

		Integer valor, clave, valor_aux, clave_aux;
		Vector<Vector<Integer>> V= new Vector<Vector<Integer>>(2);
		Set<Integer> claves= new HashSet<Integer>();
		Random r= new Random();
		
		//Clave inválida
		try {
			 s.get(null);
			 fail("El método get debería haber lanzado la excepción InvalidKeyException para una clave nula");
		} catch (InvalidKeyException e) {	
		}
		
		// Mapeo vacío.
		try {

			valor = s.get(i1);
			assertTrue(
					"El Mapeo debería devolver nulo cuando la clave no está en el mapeo.",
					valor == null);

			// Insertando 3 elementos.

			s.put(i1, n1);

			s.put(i2, n2);

			s.put(i3, n3);

		
		} catch (InvalidKeyException e) {

			fail("El método put(K k, V v) no debería lanzar InvalidkeyException con una clave válida.");

		}

		
		try {

			valor = s.get(i1);

			assertTrue("get(K k) no funciona correctamente", (valor == n1));

			s.remove(i1);

			valor = s.get(i2);

			assertTrue("get(K k) no funciona correctamente", (valor == n2));

			s.remove(i2);

			valor = s.get(i3);

			assertTrue("get(K k) no funciona correctamente", (valor == n3));

			s.remove(i3);

			

		} catch (InvalidKeyException e) {

			fail("El método get(K key) o remove(K key) no debería lanzar la excepción InvalidKeyException con claves válidas.");

		}

		// Insertando 10000 elementos
        V.add(new Vector<Integer>(10000));
        V.add(new Vector<Integer>(10000));
		try {
			for (int i=0; i<10000;i++)
				{clave=r.nextInt(10*(i+1));
				 while(claves.contains(clave))
				  	{
				 	 clave=r.nextInt(10*(i+1));	 
				 	}
				 claves.add(clave);
				 valor=r.nextInt(10000);
				 V.get(0).add(clave);
				 V.get(1).add(valor);
				 s.put(clave, valor);
				}
            
		   } catch (InvalidKeyException e) {

			fail("El método put(K key) no debería lanzar InvalidkeyException con una clave válida.");

		   }

		//Testeando Get con 10000 elementos	

		try {
			for (int i=0; i<10000;i++)
				{clave_aux= V.get(0).get(i);
				 valor_aux= V.get(1).get(i);
				 valor=s.get(clave_aux);
				 assertTrue("get(K k) no funciona correctamente", valor == valor_aux);
				}
		}catch (InvalidKeyException e) {
				fail("El método get(K k) no debería lanzar InvalidKeyException con una clave válida.");

		}

	}


	@Test
	public void put_remove() {

		Integer valor, clave, clave_aux, valor_aux;
		Random r= new Random();
		Vector<Vector<Integer>> V= new Vector<Vector<Integer>>(2);
		Set<Integer> claves= new HashSet<Integer>();
		
		//Clave Inválida
		try {
			 s.remove(null);
			 fail("El método remove debería haber lanzado la excepción InvalidKeyException para una clave nula");
		} catch (InvalidKeyException e) {	
		}
		
		try {
             s.put(null,n1);
			 fail("El método put debería haber lanzado la excepción InvalidKeyException para una clave inválida");
		} catch (InvalidKeyException e) {	
		}
	
		
		// Mapeo vacio

		try {
			valor = s.remove(i1);

			assertTrue("El valor debería ser nulo", valor == null);
		} catch (InvalidKeyException e) {
			fail("El método remove(K k) no debería lanzar InvalidKeyException con una clave válida.");
		}
		
		// Insertando put 10000 elementos.
        V.add(new Vector<Integer>(10000));
        V.add(new Vector<Integer>(10000));
		try {
			for (int i=0; i<10000;i++)
				{clave=r.nextInt(10*(i+1));
				 while(claves.contains(clave))
					clave=r.nextInt(10*(i+1));	 
				 claves.add(clave);
				 valor=r.nextInt(10000);
				 V.get(0).add(clave);
				 V.get(1).add(valor);
				 s.put(clave, valor);
				 assertTrue("Put no actualizó correctamente el size", s.size() == i+1);
				}
            
		} catch (InvalidKeyException e) {
			fail("El método put(K key) no debería lanzar InvalidkeyException con una clave válida.");
		}

		//Testeando remove con 10000 elementos	

		try {
			for (int i=9999; i>=0;i--)
				{clave_aux= V.get(0).get(i);
				 valor_aux= V.get(1).get(i);
				 valor=s.remove(clave_aux);
				 assertTrue("remove(K k) no funciona correctamente", valor == valor_aux);
				 assertTrue("remove(K k) no actualiza correctamente el size", s.size() == i);
				 assertTrue("remove(K k) no elimina correctamente la entrada", s.remove(clave_aux) == null);
				}
		} catch (InvalidKeyException e) {
				fail("El método remove(K k) no debería lanzar InvalidKeyException con una clave válida.");
		}
		
		//Mapeo vacío.

		try {
			valor = s.remove(i1);
			assertTrue("El valor debería ser nulo", valor == null);
		} catch (InvalidKeyException e) {
			fail("El método remove(K k) no debería lanzar InvalidKeyException con una clave válida.");
		}
		
		assertTrue("El mapeo debería haber quedado vacío", s.isEmpty());
		
	}

	/* TESTEANDO LOS METODOS entries(), keys() y values() */
	@Test
	public void Keys_values_entry()
	{ Iterable<Entry<Integer,Integer>> It_entries;
	  Iterable<Integer> It_values;
	  Iterable<Integer> It_keys;
	  Integer clave,valor;
	  Set<Integer> claves= new HashSet<Integer>();
	  Set<Integer> valores= new HashSet<Integer>();
	  Random r=new Random();
	  LinkedHashMap<Integer, Integer> entradas= new LinkedHashMap<Integer,Integer>();

	  //Mapeo vacío
		It_entries=s.entries();
		It_values=s.values();
		It_keys=s.keys();
			
		assertTrue("El método entries no funciona correctamente para un mapeo vacío", !It_entries.iterator().hasNext());
	    assertTrue("El método values no funciona correctamente para un mapeo vacío", !It_values.iterator().hasNext());
        assertTrue("El método keys no funciona correctamente para un mapeo vacío", !It_keys.iterator().hasNext());
		
        //Insertando 10000 entradas en el mapeo		
    	try {
			for (int i=0; i<10000;i++)
				{ clave=r.nextInt(10*(i+1));
				  while(claves.contains(clave))
				  	 clave=r.nextInt(10*(i+1));	 
			 	  claves.add(clave);
				  valor=r.nextInt(10000);
				  while(valores.contains(valor))
				 	 valor=r.nextInt(10000);	 
				  valores.add(valor);
				  entradas.put(clave, valor);
				  s.put(clave, valor);
				  assertTrue("Put no actualizó correctamente el size", s.size() == i+1);
				}
    	} catch (InvalidKeyException e) {
    		fail("El método put(K key) no debería lanzar InvalidkeyException con una clave válida.");
		}
		
		//Testeando Keys()
		 It_keys=s.keys();
		
		 for(Integer key :It_keys)
			 {	assertTrue("El método keys() no funciona correctamente", claves.contains(key));
		      	claves.remove(key);
		     }
		 assertTrue("keys() no recorre todas las claves del mapeo", claves.isEmpty());	
		
		 //Testeando values()
		 It_values=s.values();
		
		 for(Integer value :It_values)
			 {	assertTrue("El método values() no funciona correctamente", valores.contains(value));
		      	valores.remove(value);
		     }
		 assertTrue("values() no recorre todas las claves del mapeo", valores.isEmpty());	
	   
		//Testeando entries()
		 It_entries=s.entries();
		
		 for(Entry<Integer,Integer> en :It_entries)
			 {	valor=entradas.get(en.getKey());
			  	assertTrue("El método entries() no funciona correctamente", valor!=null);
			  	assertTrue("El método entries() no funciona correctamente", valor==en.getValue());
			  	entradas.remove(en.getKey());
			 }
		 assertTrue("entradas() no recorre todas las claves del mapeo", entradas.isEmpty());	
	
	}	
	
	
}