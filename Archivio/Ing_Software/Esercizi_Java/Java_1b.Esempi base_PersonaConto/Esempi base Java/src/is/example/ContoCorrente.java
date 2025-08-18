package is.example;

public class ContoCorrente {
	
	public ContoCorrente(int saldo) {
		super();
		this.saldo = saldo;
	}
	
    /*
     * Costruttore di copie.
     */
	ContoCorrente(ContoCorrente c) {
        // In Java è possibile invocare un costruttore all'interno
        // di un altro costruttore.
		this(c.saldo);
        
        // Un'altra soluzione sarebbe stata inizializzare
        // singolarmente i campi:
        //
       // this.saldo = c.saldo;
	}
	
	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
   
	private int saldo;
}
