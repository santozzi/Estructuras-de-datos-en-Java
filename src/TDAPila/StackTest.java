
/**
 * Class: StackTest
 * @author María Luján Ganuza (mlg@cs.uns.edu.ar)
 *         Estructuras de Datos _ Primer Cuatrimestre 2013
 *         Departamento de Cs. e Ing. de la Computación.
 *  @version: 2.0
 */

package TDAPila;
import org.junit.*;                         //For tags
import static org.junit.Assert.*;           //For assertions


public class StackTest {
	private Stack<String> s; //interface
	private String n1,n2,n3;
 

	private Stack<String> getStack()
	{
	  return new LinkedStack<String>();
	  
	 }
	
	/*
	 *Inicializa la pila antes de cada test individual
	 *
	 */
	@Before public void setUp()
	{   s  = getStack();
		n1 = "Uno";
		n2 = "Dos";
		n3 = "Tres";
		
	}
	/*_______________________TESTEAMOS EL METODO size()_____________________________*/
	
	@Test public void size1()
		{
			assertTrue("Tamaño de la pila justo después de ser creada != 0",s.size() == 0);
		  s.push(n1);
			assertTrue("Tamaño de la pila luego de apilar una vez != 1", s.size() == 1);
		  s.push(n2);
			assertTrue("Tamaño de la pila luego de apilar dos veces != 2", s.size() == 2);
		  s.push(n3);
			assertTrue("Tamaño de la pila luego de apilar tres veces != 3", s.size() == 3);
		 }

	@Test public void size2()
		{
		  s.push(n1);
		  s.push(n2);
		  s.push(n3);
		  try{
			  s.pop();
			  assertTrue("El tamaño de la pila luego de apilar 3 veces y desapilar 1 vez != 2", s.size() == 2);
			  s.pop();
			  assertTrue("El tamaño de la pila luego de apilar 3 veces y desapilar 2 veces != 1", s.size() == 1);
			  s.pop();
			  assertTrue("El tamaño de la pila luego de apilar 3 veces y desapilar 3 veces != 0", s.size() == 0);
		  	} catch (EmptyStackException e){ fail("Al desapilar en una pila con elementos lanza la excepción EmptyStackException"); }
		}
	
	/*_______________________TESTEAMOS EL METODO isEmpty()_____________________________*/
	
	  @Test public void isEmpty()
	  {
	    	assertTrue("La pila no está vacía justo después de ser creada", s.isEmpty());
		    s.push(n1);
			assertFalse("La pila está vacía justo después de apilar un elemento", s.isEmpty());
			try{
				s.pop();
				assertTrue("La pila no está vacía luego de desapilar el único elemento que contenía", s.isEmpty());
				} catch (EmptyStackException e){fail("Al desapilar en una pila con un elemento lanza la excepción EmptyStackException");}
	  }
 
	  /*_______________________TESTEAMOS EL METODO top()_____________________________*/
	 
	  @Test public void top()
		{try {
		    s.top();
	        fail("Al ver el tope de una pila vacía (de Strings) no lanza la excepción EmptyStackException");
	    } catch (EmptyStackException e){}
		  try{
			  s.push(n1);
			  assertSame("Tope 1 ", n1,s.top());
			  s.push(n2);
			  assertSame("Tope 2", n2,s.top());
			  s.push(n3);
			  assertSame("Tope 3", n3,s.top());
		  }catch (EmptyStackException e) { fail("Al ver el tope de una pila con elementos lanza la excepción EmptyStackException");}
		
		}
	  
	  /*_______________________TESTEAMOS EL METODO push() y pop()_____________________________*/
	  @Test public void pushPop()
  {
	  s.push(n1);
	  s.push(n2);
	  s.push(n3);
	  try{
		  assertSame("primer apilada", n3,s.pop());
		  assertSame("segunda apilada",n2,s.pop());
		  assertSame("tercer apilada", n1,s.pop());
		  assertTrue("luego de 3 apiladas seguidas de 3 desapiladas, tamaño != 0", s.size() == 0);
	  }catch (EmptyStackException e) {fail("Al desapilar en una pila con elementos lanza la excepción EmptyStackException"); }
      
	  try {
		  s.pop();
		  fail("Al desapilar en una pila vacía (de Strings) no lanza la excepción EmptyStackException");
	  } catch (EmptyStackException e){}
    
	  for (int i=0; i<1000; i++)
		  s.push(String.valueOf(i));
	  try{
	  for (int i=999; i>=0; i--)
		  assertEquals("al desapilar "+i,String.valueOf(i),s.pop());
	  }catch (EmptyStackException e){ fail("Al desapilar en una pila vacía con elementos lanza la excepción EmptyStackException");}
	  assertTrue("luego de 1000 apiladas y 1000 desapiladas, tamaño != 0", s.size() == 0);
      try {
    	  s.pop();
	      fail("al desapilar en una pila vacía (de enteros) no lanza la excepción EmptyStackException");
	  } catch (EmptyStackException e){}
}
 

}
  
  
