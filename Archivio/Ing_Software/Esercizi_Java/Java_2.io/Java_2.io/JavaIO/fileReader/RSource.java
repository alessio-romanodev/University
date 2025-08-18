import java.io.*;
public class RSource {
    public static void main(String[] arguments) {
      long t1,t2;
        try {
            FileReader file = new FileReader("news.txt");
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;
            t1=System.currentTimeMillis();
            String line ;
            while ((line = buff.readLine()) != null) //(!eof){
                //String line = buff.readLine();
                //if (line == null)
                 //  eof = true;
                //else
                   System.out.println(line);                   
          //  }
            t2=System.currentTimeMillis();
            System.out.println("Tempo di Elaborazione: "+(t2-t1)+" ms");
            buff.close(); file.close();
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        }
    }
}
