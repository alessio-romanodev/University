package is.rubrica.esempioaula;

import is.rubrica.esempioaula.persistant.PersistantIdentifiable;


public abstract class Contatto implements EmailAddressable, PersistantIdentifiable {
	
	public Contatto(String telefono, String email) {
		super();
		this.telefono = telefono;
		this.email = email;
		
	}
	
	public Contatto(Contatto c) {
		this(c.telefono, c.email);
	}
	
	public abstract String[] getEmailAddress();

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public abstract void print();
	
	public abstract boolean equals(Object o);
	
	/*
	 * Il metodo setPersistanceID dovrebbe essere non raggiungibile dall'utente ma 
	 * accessibile dal package is.rubrica.esempioaula.persistant.
	 * Tuttavia, per semplicita', lo abbiamo reso public nell'interfaccia PersistantIdentifiable.
	 * 
	 * PersistantID corrisponde alla chiave primaria ID utilizzata per la memorizzazione della
	 * entita' nello schema logico del RDBMS.
	 */
	@Override
	public String getPersistantID() {
		return persistantID;
	}

	@Override
	public void setPersistantID(String persistantID) {
		this.persistantID = persistantID;
	}

	private String telefono;
	private String email;
	private String persistantID;
}
