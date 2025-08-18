package is.jVehicle;

import java.util.ArrayList;

public class Inventario {

	public Inventario() {
		listaInventario = new ArrayList<ElementoInventario>();
	}
	
	public int size() {
		return listaInventario.size();
	}

	public boolean isEmpty() {
		return listaInventario.isEmpty();
	}

	public boolean contains(ElementoInventario o) {
		return listaInventario.contains(o);
	}


	public ElementoInventario get(int index) {
		return listaInventario.get(index);
	}


	public boolean add(ElementoInventario e) {
		return listaInventario.add(e);
	}

	public boolean remove(ElementoInventario o) {
		return listaInventario.remove(o);
	}
	
	public void printInventario() {
		for (ElementoInventario e : listaInventario) {
			/*
			 * for (int i = 0; i < listaInventario.size(); i++) {
			 * 		ElementoInventario e = listaInventario.get(i);
			 * }
			 *
			 * for (java.util.Iterator<ElementoInventario> it = listaInventario.iterator(); it.hasNext(); ) {
			 * 		ElementoInventario e = it.next();
			 * }
			 */
			e.printElementoInventario();
		}
	}


	private java.util.List<ElementoInventario> listaInventario;
}
