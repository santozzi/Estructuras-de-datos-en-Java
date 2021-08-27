package TDAArbol;

import TDALista.InvalidPositionException;
import TDALista.Position;

public class TesterArbol {
   public static void main(String[] args) {
	   Tree<Integer> arbol = new ArbolConLista<Integer>();
	   try {
		 arbol.createRoot(1);
		 Position<Integer> raiz = arbol.root();
		 Position<Integer> dos = arbol.addFirstChild(raiz, 2);
		 Position<Integer> tres =arbol.addLastChild(raiz, 3);
		 
		 Position<Integer> cuatro =arbol.addLastChild(tres, 4);
		 Position<Integer> cinco =arbol.addLastChild(tres, 5);
		 Position<Integer> seis =arbol.addLastChild(cuatro, 6);
	
		 for(Position<Integer> num : arbol.positions() ) {
		     System.out.println(num.element());
		 }
	
		
		 
		
	} catch (InvalidOperationException | EmptyTreeException | InvalidPositionException e) {
		System.out.println(e.getMessage());
	}
   }
}
