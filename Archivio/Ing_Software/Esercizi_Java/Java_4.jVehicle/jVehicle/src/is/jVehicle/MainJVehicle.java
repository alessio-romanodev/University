package is.jVehicle;

public class MainJVehicle {


	public static void main(String[] args) {
		Inventario inventario = new Inventario();
		inventario.add(new Automobile("CX81140", 2008, 410, 56, "Peugeot 206"));
		inventario.add(new Automobile("KZ46201", 2013, 525, 78, "Alfa Romeo Giuletta"));
		inventario.add(new Ciclomotore("KZ46201", 2013, 525, 78));
		
		inventario.printInventario();
	}

}
