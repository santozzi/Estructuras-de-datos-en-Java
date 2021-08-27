package TDAColaConPrioridad;



public class Comparator <K extends Comparable<K>> implements java.util.Comparator<K>  {
   
	@Override
	public int compare(K o1, K o2) {
	
		return o1.compareTo(o2);
	}
	
 
}
