package it.unina.p2.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteArrayIOApp {

	public static void main(String[] args) throws IOException {

		
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			
			String s = "Questo è un test ...";

			outStream.write(s.getBytes());
			
			//outStream.write(ByteBuffer.wrap(new byte[8]).putDouble(20).array());
						
			System.out.println("Flusso di output "+outStream);
			System.out.println("Dimensione: "+outStream.size());

			ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray());
			
			int inBytes = inStream.available();
			byte inBuf[] = new byte[inBytes];
			
			int bytesRead = inStream.read(inBuf, 0, inBytes);
			
			//double d = ByteBuffer.wrap(inBuf).getDouble();
			//System.out.println(d);
			
			System.out.println(bytesRead+" byte letti");
			System.out.println("Sono: "+new String(inBuf));
	}

}
