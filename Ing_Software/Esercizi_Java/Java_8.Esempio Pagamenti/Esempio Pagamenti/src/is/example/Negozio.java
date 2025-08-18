package is.example;

import java.util.ArrayList;


public class Negozio {
	private ArrayList<Articolo> articoli;
	private ArrayList<Persona> clienti;

	public Negozio() {
		articoli = new ArrayList<Articolo>();
		clienti = new ArrayList<Persona>(); 
	}
	
	/* Aggregazione: Negozio->articoli */
	public Negozio(ArrayList<Articolo> articoli_da_aggiungere) {
		articoli = new ArrayList<Articolo>(articoli_da_aggiungere.size());
		for (Articolo a:articoli_da_aggiungere) {
			aggiungiArticolo(a.getNome(), a.getQnt(), a.getPrezzo());
		}
	}
	
	public void aggiungiArticolo(String nome, int qnt, int prezzo) {
		articoli.add(new Articolo(nome, qnt, prezzo));
	}
	
	public boolean acquista(String nomeArticolo, int qnt, StrumentoPagamento s) {
		for (int i = 0; i < articoli.size(); i++) {
			Articolo a = articoli.get(i);
			if (a.getNome().equals(nomeArticolo)) {
				int price = qnt * a.getPrezzo();
				if (s.preleva(price)) {
					return true;
				}
				return false;
			}
		}
		return false;
	}
}
