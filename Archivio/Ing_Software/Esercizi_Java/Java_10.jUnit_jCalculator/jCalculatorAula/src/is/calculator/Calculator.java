package is.calculator;

public class Calculator {

	public int somma (int x, int y)
	{return x+y;}


	public int dividi(int x, int y) {
		if (y == 0) 
			return Integer.MAX_VALUE;
		return x/y;
	}
	
	public int moltiplica(int x, int y) {
		return x*y;
	}
	
	public int MCD(int x, int y) {
		if (y > x) 
			return MCD(y, x);
		if (y == 0)
			return x;
		return MCD(y, x%y);
	}
	
	public int mcm(int x, int y) {
		return (x*y)/MCD(x, y);
	}
	
}
