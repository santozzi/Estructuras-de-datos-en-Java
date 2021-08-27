package TDAMap;

public class Test {

	public static void main(String[] args) {
		Map<Integer,String> m1 = new OpenHashMap<Integer, String>();
		try {
			m1.put(1, "Sergio");
			System.out.println(m1.get(1));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
