package is.jVehicle;

public abstract class Veicolo implements ElementoInventario, Cloneable {
	
	public Veicolo(String targa, int annoImmatricolazione, int peso, int potenza) {
		this.targa = targa;
		this.annoImmatricolazione = annoImmatricolazione;
		this.peso = peso;
		this.potenza = potenza;
	}
	
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getPotenza() {
		return potenza;
	}
	public void setPotenza(int potenza) {
		this.potenza = potenza;
	}
	
	public int getAnnoImmatricolazione() {
		return annoImmatricolazione;
	}

	public void setAnnoImmatricolazione(int annoImmatricolazione) {
		this.annoImmatricolazione = annoImmatricolazione;
	}
	
	public abstract void printDatiVeicolo();

	@Override
	public String getCodice() {
		return targa;
	}

	@Override
	public int getAnnoIscrizione() {
		return annoImmatricolazione;
	}
	
	public void printElementoInventario() {
		this.printDatiVeicolo();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((targa == null) ? 0 : targa.hashCode());
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
		Veicolo other = (Veicolo) obj;
		if (targa == null) {
			if (other.targa != null)
				return false;
		} else if (!targa.equals(other.targa))
			return false;
		return true;
	}
	
	@Override
	protected Veicolo clone() {
		try {
			return (Veicolo)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String toString() {
		return "Veicolo [targa=" + targa + ", annoImmatricolazione=" + annoImmatricolazione + ", peso=" + peso
				+ ", potenza=" + potenza + "]";
	}

	private String targa;
	private int annoImmatricolazione;
	private int peso;
	private int potenza;
}
