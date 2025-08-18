//: c12:ChangeSystemOut.java
// Turn System.out into a PrintWriter.
import java.io.*;

public class ChangeSystemOut {
  public static void main(String[] args) {
	System.out.println("Hello world");  
    PrintWriter out = new PrintWriter(System.out, true);
    out.println("Hello, world");
  }
}