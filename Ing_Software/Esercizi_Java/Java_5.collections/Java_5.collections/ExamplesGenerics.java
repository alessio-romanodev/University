package it.unina.p2.collections;

import java.util.*; 

public class ExamplesGenerics {


	
public static void main(String[] args) {
	
	
	Object someObject = new Object();
	Integer someInteger = 10;
	someObject = someInteger;   // OK
	
	Box<Number> box = new Box<Number>();

	box.set(10);     // OK
	box.set(10.1);  // OK

	Box<Integer> boxInt = new Box<Integer>();
	
	boxTest(boxInt);
	
	List list = new ArrayList();
	list.add("hello");
	
	list.add(30);
	list.add(30.0); 
 
	
	printList(list); 
	
	Box<Integer> integerBox = new Box<>();

	Pair<String, Integer> p1 = new OrderedPair<>("Even", 8);

	Pair<String, String>  p2 = new OrderedPair<>("hello", "world");

	Box rawBox = new Box();           // rawBox is a raw type Box<Integer> intBox = rawBox;     // warning: unchecked conversion 
	Box<Integer> intBox = rawBox; 
	
	
	
	
	Integer[] intVec = new Integer[3]; 
	intVec[0] = 3; 
	intVec[1] = 5;
	intVec[2] = 1;
	int result = countGreater(intVec, 4); 
	System.out.println("Result :"+ result); 
	
}

public static void boxTest(Box<? extends Number> n) { /* ... */ }


public static void printList(List<Object> list) {
    for (Object elem : list)
        System.out.println(elem + " ");
    System.out.println();
}



public static <T extends Comparable<T>> int countGreater (T[] anArray, T elem) {
    int count = 0;
    for (T e : anArray)
        if (e.compareTo(elem) > 0)
            ++count;
    return count;
}



}


 class Box<T> {
    // T stands for "Type”
    private T t;

    public void set(T t) { this.t = t; }
    public T get() { return t; }
} 

 interface Pair<K, V> {
	    public K getKey();     public V getValue();
	}

  class OrderedPair<K, V> implements Pair<K, V> {
	    private K key;     private V value;

	    public OrderedPair(K key, V value) {
		this.key = key; 	this.value = value;
	    }
	    public K getKey()	{ return key; }
	    public V getValue() { return value; }
	}
  
  
  class Util {
	    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
	        return p1.getKey().equals(p2.getKey()) && 
	                    p1.getValue().equals(p2.getValue());
	    }
	}


	