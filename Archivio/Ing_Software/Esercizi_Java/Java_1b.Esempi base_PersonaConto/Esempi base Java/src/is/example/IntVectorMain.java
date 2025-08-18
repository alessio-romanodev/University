package is.example;

public class IntVectorMain {

	public static void main(String[] args) {
		IntVector v = new IntVector(3);
		v.set(0, 3);
		v.set(1, -1);
		v.set(2, 5);
		v.add(9);
		
		for (int i = 0; i < v.size(); i++) {
			System.out.println(v.get(i));
		}
        
        // Cosa succede se accedo ad un indice non valido?
        // Chi solleva l'exception?
       // int x = v.get(100);
        
     }

}
