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
    
    static double availability = 0;
    static String md5;
    
    public static void main(String args[]) {
    	
    	System.out.println("Hello, Switch statement.");
    	int testingSwitch = 3;
    	
    	switch(testingSwitch) {
    		case 1: testingSwitch = 1; {
    			System.out.println("This is the first option");
    		}
    		case 2: testingSwitch = 2; {
    			System.out.println("This is the second option");
    		}
    		case 3: testingSwitch = 3; {
    			System.out.println("This is the third option.");
    		}
    	}
    	
    	System.out.println("Hello, multi-deminsional array.");
    	
    	int[][] multiDArray = new int[2][2];
    	multiDArray[0][0] = 1;
    	multiDArray[0][1] = 2;
    	multiDArray[1][0] = 3;
    	multiDArray[1][1] = 4;
    	
    	System.out.println("The bottom right position in the multi-deminsional array 'multiDArray' has the value of: " + multiDArray[1][1]);
    	
    	int random = (int) (Math.random() * 10) + 0;
    	System.out.println("Here is a random number using a built-in math function: " + random);
    	
    	
    	
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
    	
    	System.out.println("\nOptions: (Type a number to select an option)\n1. MD5 Un-Hasher");
    	String input = scanner.nextLine();
    	
    	if(input.equals("1")) {
    		System.out.println("\n(Encrypted text must be under 4 characters)\nEnter MD5 Hash:");
        	Scanner sc = new Scanner(System.in);
        	md5 = sc.nextLine();
        	connect(md5);
    	} else if (input.equals("")) {
    		System.out.println("Please choose a valid option.");
    		next();
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
            	next();
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