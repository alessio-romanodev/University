package is.jVehicle;

public class Ciclomotore extends Veicolo {

	public Ciclomotore(String targa, int annoImmatricolazione, int peso, int potenza) {
		super(targa, annoImmatricolazione, peso, potenza);
	}

	@Override
	public void printDatiVeicolo() {
		System.out.println("Ciclomotore:");
		System.out.println("\t\tTarga:" + this.getTarga());
		System.out.println("\t\tAnno:" + this.getAnnoImmatricolazione());
		System.out.println("\t\tPotenza:" + this.getPotenza());
	}

}
