import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
 
public class client
{
 
    private static Socket socket;
    
    static int availability = 0;
    static String md5;
    
    public static void main(String args[]) {
    	
    	System.out.println("Making connection to server...");
    	connect("connection test");
    	if(availability == 1) {
    		System.out.println("Connection successful.");
    		next();
    	} else {
    		System.out.println("Connection failed.");
    	}
    	
    	
    }
    
    public static void next() {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("\nOptions: (Type a number to select an option)\n1. MD5 Un-Hasher\n2. Register");
    	int input = scanner.nextInt();
    	scanner.nextLine();
    	
    	if(input == 1) {
    		System.out.println("\n(Encrypted text must be under 4 characters)\nEnter MD5 Hash:");
        	Scanner sc = new Scanner(System.in);
        	md5 = sc.nextLine();
        	connect(md5);
    	} else if(input == 2) {
    		connect("register");
    	} else {
    		System.out.println("Please choose a valid option.\n");
    		next();
    	}
    }
 
    
    public static void login() {
    	
    }
    
    public static void waitingoncrack() {
    	connect("waiting");
    	next();
    }
    
    public static void connect(String sendMessage)
    {
        try
        {
            String host = "localhost";
            int port = 25000;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
 
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            String Message = sendMessage + "\n";
            bw.write(Message);
            bw.flush();
 
            //Get the return message from the server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            
            if(message.equals("true")) {
            	availability = 1;
            } else if(message.equals("cracker")) {
            	
            } else if(message.equals("cracking...")) {
            	System.out.println(message);
            	waitingoncrack();
            } else {
            	System.out.println(message);
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}