package TDACola;

import static org.junit.Assert.*;


import org.junit.*;


public class LinkedQueueTest {
	private Queue<String> c; //interface
	private String n1,n2,n3;
	
	/*
	 *Inicializa la cola antes de cada test individual
	 *
	 */

	private Queue<String> getQueue()
	{
	  return new LinkedQueue<String>();
	 }
	
	@Before public void setUp()
	{
		c  = getQueue();
		n1 = "Uno";
		n2 = "Dos";
		n3 = "Tres";
		
				
	}
	
	
	/*_______________________TESTEAMOS EL METODO size()_____________________________*/
	
	@Test public void size1()
	{
		assertTrue("Tamaño de la cola justo después de ser creada != 0",c.size() == 0);
	  c.enqueue(n1);
		assertTrue("Tamaño de la cola luego de insertar un elemento != 1", c.size() == 1);
	  c.enqueue(n2);
		assertTrue("Tamaño de la cola luego de insertar dos elementos != 2", c.size() == 2);
	  c.enqueue(n3);
		assertTrue("Tamaño de la cola luego de insertar tres elementos != 3", c.size() == 3);
	 }

	@Test public void size2()
	{
		c.enqueue(n1);
		c.enqueue(n2);
		c.enqueue(n3);
		try{
		c.dequeue();
		  assertTrue("El tamaño de la cola luego de insertar 3 elementos y eliminar uno es != 2", c.size() == 2);
		c.dequeue();
		  assertTrue("El tamaño de la cola luego de insertar 3 elementos y eliminar dos es != 1", c.size() == 1);
		c.dequeue();
		  assertTrue("El tamaño de la cola luego de insertar 3 elementos y eliminar los tres es != 0", c.size() == 0);
	  	} catch (EmptyQueueException e){ fail("Al eliminar un elemento de una cola que no está vacía lanza la excepción EmptyQueueException"); }
	}

 /*_______________________TESTEAMOS EL METODO isEmpty()_____________________________*/
	
	@Test public void isEmpty()
	{
		assertTrue("La cola no está vacía justo después de ser creada", c.isEmpty());
	    c.enqueue(n1);
		assertFalse("La cola está vacía justo después de insertar un elemento", c.isEmpty());
		try{
			c.dequeue();
			assertTrue("La cola no está vacía luego de eliminar el único elemento que contenía", c.isEmpty());
			} catch (EmptyQueueException e){fail("Al eliminar un elemento de una cola con un único elemento lanza la excepción EmptyQueueException");}
	}
	
/*_______________________TESTEAMOS EL METODO front()_____________________________*/
	 
	  @Test public void front()
		{try {
		    c.front();
	        fail("Al ver el elemento al frente de una cola vacía (de Strings) no lanza la excepción EmptyQueueException");
	    } catch (EmptyQueueException e){}
		  try{
			c.enqueue(n1);
			  assertSame("Tope 1 ", n1,c.front());
			c.enqueue(n2);
			  assertSame("Tope 2", n1,c.front());
			c.enqueue(n3);
			  assertSame("Tope 3", n1,c.front());
			c.dequeue();
			 assertSame("Tope 1 ", n2,c.front());
			 c.dequeue();
			 assertSame("Tope 1 ", n3,c.front());
		  }catch (EmptyQueueException e) { fail("Al ver el tope de una cola con elementos lanza la excepción EmptyQueueException");}
		
		}
	  
/*_______________________TESTEAMOS EL METODO enqueue() y dequeue()_____________________________*/
	  @Test public void enqueueDequeue()
  {
	  c.enqueue(n1);
	  c.enqueue(n2);
	  c.enqueue(n3);
	  try{
		  assertSame("Eliminamos el elemento al frente de la cola", n1,c.dequeue());
		  assertSame("Eliminamos el elemento al frente de la cola",n2,c.dequeue());
		  assertSame("Eliminamos el elemento al frente de la cola", n3,c.dequeue());
		  assertTrue("luego de insertar 3 elementos y seguidamente eliminar 3 elementos, tamaño != 0", c.size() == 0);
	  }catch (EmptyQueueException e) {fail("Al eliminar elementos de una cola que no está vacía lanza la excepción EmptyQueueException"); }
      
	  try {
		  c.dequeue();
		  fail("Al intentar eliminar elementos de una cola vacía (de Strings) no lanza la excepción EmptyQueueException");
	  } catch (EmptyQueueException e){}
	 
		  
	  for (int i=0; i<1000; i++)
		  {c.enqueue(String.valueOf(i));}
	  
	  
	  try{  
	  for (int i=999; i>=0; i--)
		  assertEquals("al eliminar "+i,String.valueOf(999-new Integer(i)),c.dequeue());
	  }catch (EmptyQueueException e){ fail("Al eliminar elementos de una cola que no está vacía lanza la excepción EmptyQueueException");}
	  assertTrue("luego de insertar 1000 elementos y eliminar 1000 elementos, tamaño != 0", c.size() == 0);
      try {
    	  c.dequeue();
	      fail("al intentar eliminar un elemento de una cola vacia no lanza la excepción EmptyQueueException");
	  } catch (EmptyQueueException e){}
}
 
}
