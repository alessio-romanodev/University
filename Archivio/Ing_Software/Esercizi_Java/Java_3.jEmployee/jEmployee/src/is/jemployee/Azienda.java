package is.jemployee;

public class Azienda {
	
	public Azienda(int maxImpiegati) {
		impiegatiInDB = 0;
		db = new Impiegato[maxImpiegati];
	}
	
	public boolean aggiungiImpiegato(Impiegato i) {
		if (impiegatiInDB == db.length || findImpiegato(i.getName()) != null)
			return false;
		db[impiegatiInDB++] = i;  
		return true;
	}
	
	public Impiegato findImpiegato(String name) {
		for (int i = 0; i < impiegatiInDB; i++)
			if (db[i].getName().equals(name))
				return db[i];
		return null;
	}
	
	public boolean removeImpiegato(String name) {
		for (int i = 0; i < impiegatiInDB; i++)
			if (db[i].getName().equals(name)) {
				for (int j = i+1; j < impiegatiInDB; j++)
					db[j-1] = db[j];
				impiegatiInDB--;
				return true;
			}
		return false;
	}
	
	public void alteraStipendio(double percent) {
		for (int i = 0; i < impiegatiInDB; i++) {
			int newStipendio = (int)(db[i].getStipendio() * (1+percent));
			db[i].setStipendio(newStipendio);
		}
	}
	
	public void stampaStipendi() {
		System.out.println("------ Inizio stampa stipendi ------");
		for (int i = 0; i < impiegatiInDB; i++) {
			System.out.format("Impiegato %s, stipendio %d\n", db[i].getName(), db[i].getStipendio());
			System.out.println("Impiegato "+db[i].getName()+", stipendio "+db[i].getStipendio());
		}
		System.out.println("------ Fine stampa stipendi ------");
		System.out.println();
	}
	
	private Impiegato db[];
	private int impiegatiInDB;
}
