package is.rubrica.esempioaula;

public class ContattoDiPersona extends Contatto {
	
	public ContattoDiPersona(String telefono, String email, String nome,
			String cognome, String ddn) {
		super(telefono, email);
		
		/*
		 * Eccezioni unchecked. Ci si aspetta che non si dovrebbe mai costruire un contatto
		 * di persona senza specificare i dettagli essenziali dell'istanza.
		 * 
		 * Rispondono al requisito:
		 * Non e' possibile creare un contatto se almeno uno dei parametri su cui si effettua 
		 * il confronto non e' specificato o e' vuoto. 
		 */
		if (this.nome == null || this.nome.isEmpty()) {
			throw new IllegalArgumentException("The field nome cannot be null or empty!");
		}
		
		if (this.cognome == null || this.cognome.isEmpty()) {
			throw new IllegalArgumentException("The field cognome cannot be null or empty!");
		}
		
		if (this.ddn == null || this.ddn.isEmpty()) {
			throw new IllegalArgumentException("The field ddn cannot be null or empty!");
		}
		
		this.nome = nome;
		this.cognome = cognome;
		this.ddn = ddn;
	}
	
	public ContattoDiPersona(ContattoDiPersona c) {
		this(c.getTelefono(), c.getEmail(), c.getNome(), c.getCognome(), c.getDdn());
	}
	
	@Override
	public void print() {
		System.out.format("Contatto di persona: %s %s [%s], tel: %s, email %s\n", this.getNome(), 
				this.getCognome(), this.getDdn(), 
				this.getTelefono(), this.getEmail());
	}
	
	@Override
	public String[] getEmailAddress() {
		return new String[] { String.format("%s %s <%s>", this.getNome(), this.getCognome(), this.getEmail()) };
	}

	/*
	 * Metodo generato automaticamente da Eclipse.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((ddn == null) ? 0 : ddn.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/*
	 * Metodo generato automaticamente da Eclipse. Abbiamo impostato per il confronto
	 * i soli attributi nome, cognome e ddn. Questo metodo verra' utilizzato dalla rubrica
	 * per confrontare l'uguaglianza di due ContattiDiPersona.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ContattoDiPersona))
			return false;
		ContattoDiPersona other = (ContattoDiPersona) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (ddn == null) {
			if (other.ddn != null)
				return false;
		} else if (!ddn.equals(other.ddn))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getDdn() {
		return ddn;
	}

	private String nome;
	private String cognome;
	private String ddn;
}
