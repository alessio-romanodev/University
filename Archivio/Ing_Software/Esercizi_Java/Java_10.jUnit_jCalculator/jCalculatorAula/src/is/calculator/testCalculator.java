package is.calculator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class testCalculator {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Calculator c = new Calculator();
		int res = c.moltiplica(5, 2);
		assertEquals("Moltiplicazione non funzionante!", 10, res);
	}

	@Test
	public void testDivisione() {
		Calculator c = new Calculator();
		int res = c.dividi(5,2);
		assertTrue(2.5==res);
		//int res = c.dividi(5,0);
		//assertEquals("Divisione non funzionante!", Integer.MAX_VALUE, res);

	}
	
	@Test
	public void testMCD() {
		Calculator c = new Calculator();
		int res = c.MCD(25, 15);
		assertEquals("MCD non corretto!", 5, res);
	}
	
	@Test
	public void testmcm() {
		Calculator c = new Calculator();
		int res = c.mcm(5, 3);
		assertEquals("mcm non corretto!", 15, res);
	}
	
	@Test
	public void testmoltiplicazione() {
		Calculator c = new Calculator();
		int res = c.moltiplica(5, -2);
		assertEquals(-10, res);
	}
	
	@Test
	public void testMCD2() {
		Calculator c = new Calculator();
		int res = c.MCD(15, 25);
		assertEquals("MCD non corretto!", 5, res);
	}
}
