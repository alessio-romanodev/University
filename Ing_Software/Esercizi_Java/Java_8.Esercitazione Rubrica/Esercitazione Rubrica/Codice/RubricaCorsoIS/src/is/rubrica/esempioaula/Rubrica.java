package is.rubrica.esempioaula;

import is.rubrica.esempioaula.persistant.ContattoDAO;
import is.rubrica.esempioaula.persistant.GruppoDAO;

import java.sql.SQLException;

public class Rubrica {
	public static void addContatto(Contatto c) throws DuplicatedElementException, RubricaException {
		if (listaContatti.contains(c)) {
			throw new DuplicatedElementException("Contatto is already contained in the address book!");
		}
		listaContatti.add(c);
		
		try {
			ContattoDAO.createContatto(c);
		} catch (SQLException e) {
			throw new RubricaException("Impossible to make Rubrica persistant!", e);
		}
	}
	
	public static void removeContatto(Contatto c) throws GroupDeletionException, RubricaException {
		if (listaContatti.contains(c)) {
			/*
			 * Utilizza il construtto Java foreach per scorrere ordinatamente
			 * gli elementi della lista. Il costrutto foreach solleva una eccezione 
			 * se il riferimento alla collection/array da iterare e' null.  
			 */
			for (Gruppo g : listaGruppi) {
				if (g.containsContatto(c)) {
					throw new GroupDeletionException("The contact is in one or more groups!"); 
				}
			}
			listaContatti.remove(c);
			try {
				ContattoDAO.deleteContatto(c);
			} catch (SQLException e) {
				throw new RubricaException("Impossible to make Rubrica persistant!", e);
			}
		}
	}
	
	public static boolean isContattoInRubrica(Contatto c) {
		return listaContatti.contains(c);
	}
	
	public static void addGruppo(Gruppo g) throws RubricaException, DuplicatedElementException, ContattoNotInRubricaException {
		if (g.isInRubrica()) {
			throw new DuplicatedElementException("Gruppo is already contained in the address book!");
		}
		if (listaGruppi.contains(g)) {
			throw new DuplicatedElementException("Another Gruppo with same label is already contained in the address book!");
		} 
		/*
		 * Utilizza il construtto Java foreach per scorrere ordinatamente
		 * gli elementi della lista. Il costrutto foreach solleva una eccezione 
		 * se il riferimento alla collection/array da iterare e' null.  
		 */
		for (Contatto c : g.getContatti()) {
			if (!isContattoInRubrica(c)) {
				throw new ContattoNotInRubricaException("Gruppo contains at least one contact that is not in Rubrica!");
			}
		}
		
		listaGruppi.add(g);
		g.setInRubrica(true);
		
		try {
			GruppoDAO.createGruppo(g);
		} catch (SQLException e) {
			throw new RubricaException("Impossible to make Rubrica persistant!", e);
		}
	}
	
	public static void removeGruppo(Gruppo g) throws RubricaException {
		if (g.isInRubrica()) {
			listaGruppi.remove(g);
			g.setInRubrica(false);
			
			try {
				GruppoDAO.deleteGruppo(g);
			} catch (SQLException e) {
				throw new RubricaException("Impossible to make Rubrica persistant!", e);
			}
		}
	}
	
	public static Contatto[] getListaContatti() {
		return listaContatti.toArray(new Contatto[listaContatti.size()]);
	}
	
	public static Gruppo[] getListaGruppi() {
		return listaGruppi.toArray(new Gruppo[listaGruppi.size()]);
	}
	
	public static void loadRubrica() throws RubricaException {
		try {
			listaContatti = ContattoDAO.readListaContatti();
			listaGruppi = new java.util.HashSet<Gruppo>(GruppoDAO.readGruppi());
			/*
			 * Utilizza il construtto Java foreach per scorrere ordinatamente
			 * gli elementi della lista. Il costrutto foreach solleva una eccezione 
			 * se il riferimento alla collection/array da iterare e' null.  
			 */
			for (Gruppo g : listaGruppi) {
				g.setInRubrica(true);
			}

		} catch (SQLException e) {
			throw new RubricaException("Impossibile to load rubrica!", e);
		}
	}
	
	public static void saveRubrica() throws RubricaException {
		try {
			/*
			 * Utilizza il construtto Java foreach per scorrere ordinatamente
			 * gli elementi della lista. Il costrutto foreach solleva una eccezione 
			 * se il riferimento alla collection/array da iterare e' null.  
			 */
			for (Contatto c : listaContatti) {
				ContattoDAO.updateContatto(c);
			}
			for (Gruppo g : listaGruppi) {
				GruppoDAO.updateGruppo(g);
			}
		} catch (SQLException e) {
			throw new RubricaException("Impossible to save rubrica!", e);
		}
	}
	
	/* Per implementare i requisiti sull'ordinamento di contatti e gruppi sono utilizzate le due strutture ArrayList e Set (con e senza ordinamento) */
	
	static private java.util.List<Contatto> listaContatti = new java.util.ArrayList<Contatto>(); 
	static private java.util.Set<Gruppo> listaGruppi = new java.util.HashSet<Gruppo>();
}
