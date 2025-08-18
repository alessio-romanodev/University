package it.unina.p2.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileStreamIOApp {

	public static void main(String[] args) throws IOException {

		File file = new File("/Users/robertopietrantuono/myFile.txt");

		
		
		try {
		    // Crea un nuovo file se non esiste
		    if (file.createNewFile()) 
		        System.out.println("File created"); 
		    else
		        System.out.println("File already exists");
		} catch (IOException x) {    
		    System.err.format("I/O error: %s%n", x.getMessage());
		}

		
		//Operazione di scrittura
		String s = "Testo di prova\n";

		byte data[] = s.getBytes();

		OutputStream out = null;
		try {
		    out = new BufferedOutputStream(new FileOutputStream(file));
		    
		    out.write(data, 0, data.length);
		} catch (IOException x) {
		    System.err.println(x);
		} finally {
		    if (out != null) {
		        out.flush();
		        out.close();
		    }
		}

		//Operazione di lettura
		InputStream in = null;
		try {
			
		    in = new FileInputStream(file);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		//  BufferedReader reader = new BufferedReader(new FileReader(file)); //InputStreamReader(in));
			
		    String line = null;
		    while ((line = reader.readLine()) != null) 
		        System.out.println(line);

		    /*   Scanner openfile = new Scanner(file);
		    while (openfile.hasNextLine()) {
		       String filedata = openfile.nextLine();
		       System.out.println(filedata);
		    }*/
		    
		/*	ArrayList<String> fileLines = (ArrayList<String>)Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			  for (int i = 0;  i < fileLines.size(); i++) {
				  System.out.println(fileLines.get(i));
			  } */
		    //Desktop class can also be used. 
		    
		   
		} catch (IOException x) {
		    System.err.println(x);
		} finally {
		    if (in != null) 
		    	in.close();		  
		}

	}

}
