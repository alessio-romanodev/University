//	 Testing for end of file while reading a byte at a time.
	import java.io.*;

	public class SequentialRead2 {
	  // Throw exceptions to console:
	  public static void main(String[] args)
	  throws IOException {
	    DataInputStream in = new DataInputStream(
	      new BufferedInputStream(
	        new FileInputStream("./FilterInputStream/SequentialRead2.java")));
	    while(in.available() != 0)
	      System.out.print((char)in.readByte());
	  }
	}
