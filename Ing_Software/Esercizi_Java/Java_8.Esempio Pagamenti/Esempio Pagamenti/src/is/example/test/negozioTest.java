package is.example.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import is.example.CartaDebito;
import is.example.Negozio;
import is.example.StrumentoPagamento;
import junit.framework.TestCase;

class negozioTest extends TestCase {

	@BeforeEach
	public
	void setUp() throws Exception {
	}

	@AfterEach
	public
	void tearDown() throws Exception {
	}

	@Test
	void testAcquisto() {
		
		Negozio neg =new Negozio();
		StrumentoPagamento c = new CartaDebito();
		((CartaDebito)c).deposita(100);
		int saldoCorrente = c.getSaldo();
		System.out.println("saldoCorrente "+saldoCorrente);
		int quantityToAdd = 5;
		int prezzo = 10;
		neg.aggiungiArticolo("Articolo 1", quantityToAdd , prezzo );
		int quantityToBuy = 2;
		neg.acquista("Articolo 1", 2, c);
		assertEquals("Acquisto Non valido!",c.getSaldo(),saldoCorrente-(quantityToBuy * prezzo));
		
	}

}
