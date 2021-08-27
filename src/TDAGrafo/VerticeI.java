package TDAGrafo;

import TDALista.Position;

public class VerticeI<V> extends Vertice<V> {
    protected int indice;

	public VerticeI(Position<Vertice<V>> pos, V rotulo, int indice) {
		super(pos, rotulo);
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
    
    
}
