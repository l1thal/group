import java.lang.*;
import java.io.*;
import java.net.*;

class client {
   public static void main(String args[]) {
	   
	   while(true) {
		   reader();
	   }
	   
   }
	   public static void reader() {
		   
      try {
         Socket skt = new Socket("localhost", 1111);
         BufferedReader in = new BufferedReader(new
            InputStreamReader(skt.getInputStream()));
         System.out.print("Received string: '");

         while (!in.ready()) {}
         System.out.print(in.readLine()); // Read one line and output it

         System.out.print("'\n");
         in.close();
      }
      catch(Exception e) {
         return;
      }  
	   }
}
