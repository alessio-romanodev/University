import java.io.*;
public class ProvaFilter {
    public static void main(String[] arguments) throws IOException {
     try {
      FileOutputStream fos= new FileOutputStream("Data.txt");
      BufferedOutputStream bos= new BufferedOutputStream(fos);
      DataOutputStream out = new DataOutputStream(bos);
      
      out.writeDouble(3.14159);
      out.writeUTF("This is pi");
      out.writeDouble(6.28318);
      out.writeUTF("This is 2*pi");
      out.writeUTF("Square root of 2"); 
      out.writeDouble(Math.sqrt(2));
      out.close();
 
      DataInputStream in = new DataInputStream(new BufferedInputStream(
       new FileInputStream("Data.txt")));      

      // Must use DataInputStream for data:
      System.out.println(in.readDouble());
      // Only readUTF() will recover the
      // Java-UTF String properly:
      System.out.println(in.readUTF());
      // Read the following double and String:
      System.out.println(in.readDouble());
      System.out.println(in.readUTF());
      // Read the following String and double:
      System.out.println(in.readUTF());
      System.out.println(in.readDouble());
      in.close();
      
      } catch(EOFException e) {
      throw new RuntimeException(e);
    }
}
}