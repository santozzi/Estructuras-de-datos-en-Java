package TDAABB;

import TDALista.Position;

public class TesterABB {

	public static void main(String[] args) {
		ABB<Persona> busquedaDePersona = new ABB<Persona>();
		Persona bernardo = new Persona("Bernardo",77);
		Persona lucio = new Persona("Lucio",70);
		Persona sergio = new Persona("Sergio",95);
		Persona maggie = new Persona("Maggie",11);
		
		busquedaDePersona.insertar(lucio);
		busquedaDePersona.insertar(bernardo);
		busquedaDePersona.insertar(sergio);
		busquedaDePersona.insertar(maggie);
		
		
		
		
		
		
		
	}

}
