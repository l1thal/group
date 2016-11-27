import java.io.*;
import java.util.Scanner;
import java.lang.*;
import java.net.*;

public class server {
	public static void main(String[] args) {
		
		
		while(true) {
			
			Scanner textinput = new Scanner(System.in);
			String input = textinput.nextLine();
			
			int decide = connection(input);
			if (decide == 1) {
				break;
			}
		}
	}
	
	
	public static int connection(String info) {
		int x;
		try {
			ServerSocket server = new ServerSocket(1111);
			Socket socket = server.accept();
			System.out.println("Client Connected.");
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			System.out.print("Sending string: '" + info + "'\n");
			out.print(info);
			out.close();
			socket.close();
			server.close();
			x = 0;
			}
		catch (Exception e) {
			System.out.println("String not sent.");
			x = 1;
		}
		return x;
	}
}
