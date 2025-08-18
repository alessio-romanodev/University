package is.example;

import java.util.ArrayList;


public class PersonaContoCorrenteMain {

	public static void scambiaValori(int c1, int c2) {
		// Esiste una implementazione che permette di scambiare
        // i valori contenuti nelle variabili del chiamante??		
	}
	
	public static void trasferisciFondo(int c1, int c2, int value) {
		int saldoC1 = c1 - value;
		int saldoC2 = c2 + value;
		
		c1 = saldoC1;
		c2 = saldoC2;
	}
	
	
	public static void trasferisciFondo(ContoCorrente c1, ContoCorrente c2, int value) {
		int saldoC1 = c1.getSaldo() - value;
		System.out.println(" saldoC1 "+saldoC1);
		int saldoC2 = c2.getSaldo() + value;
		System.out.println(" saldoC2 "+saldoC2);
		
		c1.setSaldo(saldoC1);
		c2.setSaldo(saldoC2);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

        Persona p1 = new Persona("int1", 1980);
        p1.apriContoCorrente();
        
        p1.setIntestatario("Intestario 1");
        p1.getCc().setSaldo(100);
        
        System.out.println("Persona p1 "+p1.getIntestatario());
        System.out.println("Contocorrente p1 "+p1.getCc().getSaldo());
        
        Persona p2 = new Persona(p1);
        
        p1.getCc().setSaldo(0);
        System.out.println("Persona p1 "+p1.getIntestatario());
        System.out.println("Contocorrente p1 "+p1.getCc().getSaldo());
        
        
        System.out.println("Persona p2 "+p2.getIntestatario());
        System.out.println("Contocorrente p2 "+p2.getCc().getSaldo());
        
        System.exit(0);
		
		ContoCorrente c1 = new is.example.ContoCorrente(1000);
		ContoCorrente c2 = new is.example.ContoCorrente(2500);
		
        // Il trasferimento di denaro tra i due conti c1, c2
        // è effettuato correttamente? Perché?
		trasferisciFondo(c1, c2, 500);
		System.out.println(c1.getSaldo());
		System.out.println(c2.getSaldo());
        
        // Il trasferimento di denaro tra i due conti c1int, c2int
        // è effettuato correttamente? Perché?
        int c1int = 1000;
        int c2int = 2500;
        trasferisciFondo(c1int, c2int, 500);
        System.out.println(c1int);
        System.out.println(c2int);
        
        // Il conto corrente c1 è influenzato dall'invocazione
        // del metodo trasferisciFondo(c1, c3, 500)? Perché?
        ContoCorrente c3 = new ContoCorrente(c1);
		trasferisciFondo(c1, c3, 500);
		System.out.println(c1.getSaldo());
		System.out.println(c3.getSaldo());
		
        
        // Il conto corrente c1 è influenzato dalla chiamata
        // dell'operazione trasferisciFondo(c1, c4, 500)? Perché?
        ContoCorrente c4 = c1;
        System.out.println(" --- ");
        System.out.println(c1.getSaldo());
		System.out.println(c4.getSaldo());
		
		trasferisciFondo(c1, c4, 500);
		System.out.println(" --- ");
		System.out.println(c1.getSaldo());
		System.out.println(c4.getSaldo());
		
		
    }

}
