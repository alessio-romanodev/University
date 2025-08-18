package is.jemployee;

public class Sistema {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Azienda a = new Azienda(10);
		
		Impiegato simon = new Impiegato("Simon", 12000);
		a.aggiungiImpiegato(simon);
		
		a.aggiungiImpiegato(new Impiegato("Micheal", 2500));
		a.aggiungiImpiegato(new Impiegato("John", 2000));
		a.aggiungiImpiegato(new Impiegato("Paul", 500));
		
		a.stampaStipendi();
		simon.setName("Simone");
		
		a.stampaStipendi();
		a.alteraStipendio(0.10);
		a.stampaStipendi();
		
		a.removeImpiegato("Simon");
		a.stampaStipendi();
	}

}
