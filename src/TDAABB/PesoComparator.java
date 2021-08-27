package TDAABB;

import java.util.Comparator;

public class PesoComparator implements Comparator<Persona> {

	@Override
	public int compare(Persona o1, Persona o2) {
		int aRetornar;
		if(o1.getPeso()<o2.getPeso()) {
			aRetornar =-1;
		}else if(o1.getPeso()>o2.getPeso()) {
			aRetornar = 1;
		}else {
			aRetornar =0;
		}
		return aRetornar;
	
	}

}
