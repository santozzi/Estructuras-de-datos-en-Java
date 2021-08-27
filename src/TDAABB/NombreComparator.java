package TDAABB;

import java.util.Comparator;

public class NombreComparator implements Comparator<Persona> {

	@Override
	public int compare(Persona o1, Persona o2) {
		int aRetornar;
		if(o1.getNombre().charAt(0)<o2.getNombre().charAt(0)) {
			aRetornar =-1;
		}else if(o1.getNombre().charAt(0)>o2.getNombre().charAt(0)) {
			aRetornar = 1;
		}else {
			aRetornar =0;
		}
		return aRetornar;
	}

}
