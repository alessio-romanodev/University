package is.jemployee;

public class Impiegato {
	
	public Impiegato(String name, int stipendio) {
		super();
		this.name = name;
		this.stipendio = stipendio;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the stipendio
	 */
	public int getStipendio() {
		return stipendio;
	}
	/**
	 * @param stipendio the stipendio to set
	 */
	public void setStipendio(int stipendio) {
		this.stipendio = stipendio;
	}
	private String name;
	private int stipendio;
}
