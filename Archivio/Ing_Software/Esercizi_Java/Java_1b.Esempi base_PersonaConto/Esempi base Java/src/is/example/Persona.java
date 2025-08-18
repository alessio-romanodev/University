package is.example;

public class Persona {
	private String intestatario;
	private int annoNascita;
	private ContoCorrente cc;

	
	
	public Persona(String intestatario, int annoNascita) {
		super();
		this.intestatario = intestatario;
		this.annoNascita = annoNascita;
	}
	
    // COSTRUTTORE DI COPIA
	public Persona(Persona p) {
		this.annoNascita = p.annoNascita;
		// Creo una copia in maniera corretta della variabile membro ContoCorrente?
		this.cc = new ContoCorrente(p.cc);
        // Sto creando una copia della variabile membro intestatario?
        // Perché non è necessario copiare un oggetto della classe String?
        this.intestatario = p.intestatario;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + annoNascita;
		result = prime * result + ((cc == null) ? 0 : cc.hashCode());
		result = prime * result + ((intestatario == null) ? 0 : intestatario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (annoNascita != other.annoNascita)
			return false;
		if (cc == null) {
			if (other.cc != null)
				return false;
		} else if (!cc.equals(other.cc))
			return false;
		if (intestatario == null) {
			if (other.intestatario != null)
				return false;
		} else if (!intestatario.equals(other.intestatario))
			return false;
		return true;
	}



	public void apriContoCorrente() {
		if (cc == null)
			cc = new ContoCorrente(0);
	}
	
	public ContoCorrente getCc() {
		return cc;

		//cosa cambia se faccio in questo modo? 
		//return new ContoCoorente(cc);
	}

	public String getIntestatario() {
		return intestatario;
		
		//cosa cambia se faccio in questo modo? 
		//return new String(intestatario);
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}
}

