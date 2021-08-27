package TDALista;

import java.util.Iterator;

public class ElementIterator<E> implements Iterator<E> {
    protected PositionList<E> lista;
    protected Position<E> cursor;
    
    public ElementIterator(PositionList<E> lista) {
    	this.lista = lista;
    	if(lista.isEmpty()) {
    		this.cursor=null;
    	}else {
    		try {
    			this.cursor = lista.first();
    		}catch(EmptyListException e) {
    			e.getMessage();
    		}
    	}
    	
    	
    	
    }
    
	
	
	@Override
	public boolean hasNext() {
		
		return this.cursor!=null;
	}

	@Override
	public E next() {
		
		E aRetornar = cursor.element();
		try {
			if(this.cursor!=lista.last()) {
				this.cursor = lista.next(cursor);
			}else {
				this.cursor=null;
			}
		}catch(InvalidPositionException | BoundaryViolationException | EmptyListException e) {
			e.getMessage();
		}
		
		return aRetornar;
	}

}
