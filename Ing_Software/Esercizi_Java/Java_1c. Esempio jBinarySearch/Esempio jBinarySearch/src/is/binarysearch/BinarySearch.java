package is.binarysearch;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;

public class BinarySearch {

	/**
	 * Effettua una ricerca binaria sul vettore vect di estremi start, end. Restituisce una eccezione
	 * se l'elemento ricercato non è presente.
	 * 
	 * @param vect vettore in cui effettuare la ricerca binaria.
	 * @param start primo indice del vettore.
	 * @param end ultimo indice (escluso) del vettore.
	 * @param elem l'elemento da ricercare
	 * @return la posizione di elem [start; end).
	 * @throws ElementNotFound se elem non è presente in vect.
	 */
	public static int binarySearch(int[] vect, int start, int end, int elem) throws ElementNotFound 
	{
		if (start >= end) throw new ElementNotFound();
		
		int mid = (start+end)/2;
		if (vect[mid] == elem) 
			return mid;
		else if (vect[mid] < elem)
			return binarySearch(vect, mid+1, end, elem);
		return binarySearch(vect, start, mid, elem);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int v[] = { 1, 5, 9, 11, 16 };
		
		java.io.BufferedReader bis = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		
		for (int i = 0; i < 10; i++) {
			try {
				String input = bis.readLine();
				int elem = Integer.parseInt(input);
				
				System.out.println(elem);
				int pos = binarySearch(v, 0, v.length, elem);
				
				System.out.print("Elemento trovato in posizione ");
				System.out.print(pos);
				System.out.println();
				
			} catch (IOException e) {
				System.out.println("Errore nella lettura da tastiera!");
			} catch (ElementNotFound e) {
				System.out.println("Errore elemento non trovato in vect!");
			}
			
		}
 	}

}

class ElementNotFound extends Exception {
}