package TDALista;

public class TestLista {
	public static void main(String[] args) {
		PositionList<Integer> lista= new SimpleLinkedList<Integer>();
		lista.addLast(1);
		lista.addLast(2);
		lista.addLast(3);
		try {
			Position<Integer> pos1= lista.first();
			Position<Integer> pos2= lista.first();
			System.out.println("pos1: "+pos1.element()+" pos2: "+pos2.element());
			lista.remove(pos1);
			System.out.println("pos1: "+pos1.element()+" pos2: "+pos2.element());
			
			
		} catch (EmptyListException | InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
