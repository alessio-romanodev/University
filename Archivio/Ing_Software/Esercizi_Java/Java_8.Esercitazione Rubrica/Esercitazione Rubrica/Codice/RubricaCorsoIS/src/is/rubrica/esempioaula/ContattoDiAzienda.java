package is.rubrica.esempioaula;

public class ContattoDiAzienda extends Contatto {
	private String ragioneSociale;

	/**
	 * @return the ragioneSociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public ContattoDiAzienda(String telefono, String email,
			String ragioneSociale) {
		super(telefono, email);
		
		/*
		 * Eccezioni unchecked. Ci si aspetta che non si dovrebbe mai costruire un contatto
		 * di azienda senza specificare i dettagli essenziali dell'istanza.
		 * 
		 * Rispondono al requisito:
		 * Non e' possibile creare un contatto se almeno uno dei parametri su cui si effettua 
		 * il confronto non e' specificato o e' vuoto. 
		 */
		if (ragioneSociale == null || ragioneSociale.isEmpty()) {
			throw new IllegalArgumentException("The field ragioneSociale cannot be null or empty!");
		}
		this.ragioneSociale = ragioneSociale;
	}

	
	@Override
	public void print() {
		System.out.format("Contatto di azienda: %s, tel: %s, email %s\n", this.ragioneSociale, this.getTelefono(), this.getEmail());
	}
	
	public  String[] getEmailAddress() {
		return new String[] { String.format("%s <%s>", this.getRagioneSociale(), this.getEmail()) };
	}

	/*
	 * Metodo generato automaticamente da Eclipse.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ragioneSociale == null) ? 0 : ragioneSociale.hashCode());
		return result;
	}

	/*
	 * Metodo generato automaticamente da Eclipse. Abbiamo impostato per il confronto
	 * il soli attributo ragione sociale. Questo metodo verra' utilizzato dalla rubrica
	 * per confrontare l'uguaglianza di due ContattiDiAzienda.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ContattoDiAzienda))
			return false;
		ContattoDiAzienda other = (ContattoDiAzienda) obj;
		if (ragioneSociale == null) {
			if (other.ragioneSociale != null)
				return false;
		} else if (!ragioneSociale.equals(other.ragioneSociale))
			return false;
		return true;
	}

	
	
	
	
	
}
