package TDAABB;

public class Persona implements Comparable<Persona> {
    protected String  nombre;
    protected float peso;
    
	public Persona(String nombre, float peso) {
		this.nombre = nombre;
		this.peso = peso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Persona o) {
		int aRetornar;
		if(this.peso<o.getPeso()) {
			aRetornar =-1;
		}else if(this.peso>o.getPeso()) {
			aRetornar = 1;
		}else {
			aRetornar =0;
		}
		return aRetornar;
	}
	
	public String toString() {
		return "Se llama: "+this.nombre+" pesa: "+this.peso;
	}

}
