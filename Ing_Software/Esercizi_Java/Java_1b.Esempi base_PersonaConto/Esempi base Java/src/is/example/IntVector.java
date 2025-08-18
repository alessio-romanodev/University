package is.example;

/*
 * Implementazione di un vettore dinamico di elementi int mediante
 * utilizzo di vettori statici int[].
 * Realizza in parte l'interfaccia java.util.List .
 *
 */
public class IntVector {

	private int v[];
	
	public IntVector(int size) {
		super();
		v = new int[size];
	}
	
	
	public IntVector(IntVector obj) {
		this.v = new int[obj.v.length];
		
		for (int i = 0; i < v.length; i++) {
			this.v[i] = obj.v[i];
		}
	}
	
	public void add(int n) {
	
		int v_new[] = new int[v.length + 1];
		for (int i = 0; i < v.length; i++)
			v_new[i] = v[i];
		
		v_new[v.length] = n;
		v = v_new;
	}
	
	public void remove(int id) {
		int v_new[] = new int[v.length -1];
		int v_new_pos = 0;
		
		for (int i = 0; i < v.length; i++) {
			if (i != id) {
				v_new[v_new_pos] = v[i];
				v_new_pos++;
			}
		}
		v = v_new;
	}
	
	public int get(int id) {
		return v[id];
	}
	
	public void set(int id, int n) {
		v[id] = n;
	}
	
	public int size() {
		return v.length;
	}
	
	
}
