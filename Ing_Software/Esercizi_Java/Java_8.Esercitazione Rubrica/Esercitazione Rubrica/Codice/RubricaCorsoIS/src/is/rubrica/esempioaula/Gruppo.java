package is.rubrica.esempioaula;

import is.rubrica.esempioaula.persistant.PersistantIdentifiable;

public class Gruppo implements EmailAddressable, PersistantIdentifiable {
	
	public Gruppo(String label) {
		this.label = label;
		this.setContatti = new java.util.HashSet<Contatto>();
		this.inRubrica = false;
	}
	
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	
	public boolean isInRubrica() {
		return inRubrica;
	}

	void setInRubrica(boolean inRubrica) {
		this.inRubrica = inRubrica;
	}

	public boolean addContatto(Contatto e) throws ContattoNotInRubricaException {
		if (isInRubrica() && !Rubrica.isContattoInRubrica(e)) {
			throw new ContattoNotInRubricaException("Contatto not in the address book!");
		}
		return setContatti.add(e);
	}

	public boolean removeContatto(Object o) {
		return setContatti.remove(o);
	}
	
	@Override
	public String[] getEmailAddress() {
		String[] r = new String[setContatti.size()];
		int r_id = 0;
		
		/*
		 * Utilizza il construtto Java foreach per scorrere ordinatamente
		 * gli elementi della lista. Il costrutto foreach solleva una eccezione 
		 * se il riferimento alla collection/array da iterare e' null.  
		 */
		for (Contatto c : setContatti) {
			r[r_id++] = c.getEmail();
		}
		
		return r;
	}


	@Override
	public String getPersistantID() {
		return persistanceID;
	}

	/*
	 * Il metodo setPersistanceID dovrebbe essere non raggiungibile dall'utente ma 
	 * accessibile dal package is.rubrica.esempioaula.persistant.
	 * Tuttavia, per semplicita', lo abbiamo reso public nell'interfaccia PersistantIdentifiable.
	 * 
	 * PersistantID corrisponde alla chiave primaria ID utilizzata per la memorizzazione della
	 * entita' nello schema logico del RDBMS.
	 */
	@Override
	public void setPersistantID(String persistanceID) {
		this.persistanceID = persistanceID;
	}
	
	public Contatto[] getContatti() {
		return setContatti.toArray(new Contatto[setContatti.size()]);
	}
	
	public boolean containsContatto(Contatto c) {
		return setContatti.contains(c);
	}

	private String label;
	private String persistanceID;
	private java.util.Set<Contatto> setContatti;
	private boolean inRubrica;
}
