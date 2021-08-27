package TDAGrafo.Vertices;

import TDALista.Position;

public class VerticeI<V> extends Vertice<V> {
    protected int indice;
    protected Position<VerticeI<V>> posVerticeI;
	public VerticeI(Position<VerticeI<V>> posVerticeI, V rotulo, int indice) {
		super(rotulo);
		this.posVerticeI = posVerticeI;
		this.indice=indice;
	}

	public VerticeI(V rotulo) {
		this(null,rotulo,0);
		
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public Position<VerticeI<V>> getPosVerticeI() {
		return posVerticeI;
	}

	public void setPosVerticeI(Position<VerticeI<V>> posVerticeI) {
		this.posVerticeI = posVerticeI;
	}
    
    
}
