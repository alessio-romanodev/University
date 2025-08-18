//	 Testing for end of file while reading a byte at a time.
	import java.io.*;

	public class SequentialRead {
	  // Throw exceptions to console:
	  public static void main(String[] args)
	  throws IOException {
	    DataInputStream in = new DataInputStream(
	      new BufferedInputStream(
	        new FileInputStream("SequentialRead.java")));
	    while(in.available() != 0)
	      System.out.print((char)in.readByte());
	  }
	}

