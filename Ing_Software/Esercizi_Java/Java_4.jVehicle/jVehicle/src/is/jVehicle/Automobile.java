package is.jVehicle;

public class Automobile extends Veicolo {

	public Automobile(String targa, int annoImmatricolazione, int peso, int potenza, String modello) {
		super(targa, annoImmatricolazione, peso, potenza);
		this.modello = modello;
	}

	@Override
	public void printDatiVeicolo() {
		System.out.println("Automobile:");
		System.out.println("\t\tTarga:" + this.getTarga());
		System.out.println("\t\tAnno:" + this.getAnnoImmatricolazione());
		System.out.println("\t\tModello:" + this.getModello());
	}
	
	public String getModello() {
		return modello;
	}
	
	public void setModello(String modello) {
		this.modello = modello;
	}
	
	private String modello;
}
