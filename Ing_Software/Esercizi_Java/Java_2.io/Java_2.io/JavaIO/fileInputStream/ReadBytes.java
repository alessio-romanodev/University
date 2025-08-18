import java.io.*;
public class ReadBytes {
    public static void main(String[] arguments) {
        try {
        	//System.out.println(System.getProperty("user.dir"));
            FileInputStream file = new FileInputStream("./fileInputStream/class1.dat");
            boolean eof = false;
            int count = 0;
            String tot=new String();
            while (!eof) {
            	System.out.println("\n # Bytes ancora leggibili: " + file.available());
            	
            	// restituisce un intero rappresentante il successivo byte nel flusso,
            	// o -1 se alla fine del flusso
                int input = file.read();
                System.out.print(input + " ");                
                if (input == -1)
                    eof = true;
                else{
                    count++;
                    tot+=String.valueOf((char)input);
                }
            }            
            file.close();
            System.out.println("\nBytes read: " + count);
            System.out.println("sequenza letta: "+tot);
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        }
    }
}
