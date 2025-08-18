package is.rubrica.esempioLista;

import is.rubrica.esempioaula.Contatto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Un contenitore di elementi Contatto.
 * Lista contatti possiede un array statico interno che permette di memorizzare
 * elementi contatto... ...
 * 
 * @author Fabio Scippacercola
 * @
 */
public class ListaContatti {
	
	private Contatto[] v;
	

	public ListaContatti() {
		super();
		v = new Contatto[0];
	}

	/** 
	 * Aggiunge un contatto alla lista.
	 * 
	 * @param c l'elemento da aggiungere alla lista.
	 * @return true se l'elemento e' stato aggiunto alla lista.
	 */
	public boolean add(Contatto c) {
		Contatto[] v_new = new Contatto[v.length +1];
		for (int i = 0; i < v.length; i++) {
			v_new[i] = v[i];
		}
		v_new[v.length] = c;
		v = v_new;
		return true;
	}

	/**
	 * Rimuove l'elemento c dalla lista.
	 * 
	 * @param c l'elemento da rimuovere.
	 * @return true se l'elemento era presente ed e' stato rimosso, altrimenti false.
	 */
	public boolean remove(Contatto c) {
		int pos = this.contains(c);
		if (pos >= 0) {
			Contatto[] v_new = new Contatto[v.length -1];
			int v_new_id = 0;
			
			for (int i = 0; i < v.length; i++) {
				if (i != pos) {
					v_new[v_new_id++] = v[i];
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Restituisce l'indice ove e' presente il contatto c nella lista.
	 * 
	 * @param c il contatto da ricercare nella lista.
	 * @return id dove e' presente il contatto, altrimenti -1.
	 */
	public int contains(Contatto c) {
		for (int i = 0; i < v.length; i++) {
			if (v[i] == c) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Restituisce un vettore con gli elementi presenti nella lista.
	 * 
	 * Se il vettore in ingresso a e' ampio sufficientemente per contenere gli
	 * elementi della lista, e' restituito il vettore a con gli elementi della
	 * lista. Altrimenti viene restituito un nuovo vettore contenente gli elementi
	 * della lista.
	 * 
	 * @param a vettore che se largo sufficientemente e' restituito.
	 * @return vettore con gli elementi della lista.
	 */
	public Contatto[] toArray(Contatto[] a) {
		Contatto[] r;
		if (a.length >= v.length) {
			r = a;
		} else {
			r = new Contatto[v.length];
		}
		
		for (int i = 0; i < v.length; i++) {
			r[i] = v[i];
		}
		return r;
	}
	
	
	/**
	 * Restituisce l'i-esimo elemento nella lista.
	 * 
	 * @param i indice dell'elemento.
	 * @return l'elemento i-esimo contenuto nella lista.
	 * @throws ListaContattoException 
	 */
	public Contatto get(int i) throws ListaContattoException {
		try {
			return v[i];
		} catch (Exception e) {
			throw new ListaContattoException("Accesso al di fuori dei limiti dell'array!");
		}
	}
	
	/**
	 * Restituisce la dimensione del vettore.
	 * 
	 * @return la dimensione del vettore.
	 */
	public int size() {
		return v.length;
	}
}
