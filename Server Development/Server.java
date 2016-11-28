import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.security.*;
 
public class server
{
 
    private static Socket socket;
 
    static String tobecracked;
    static boolean crackStatus = false;
    
    public static void main(String[] args) {
    	send();
    }
    
    public static void send()
    {
        try
        {
 
            int port = 25000;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port 25000\n");
 
            //Server is running always. This is done using this while(true) loop
            while(true)
            {

                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String number = br.readLine();
                System.out.println("Message received from client is: "+number);
 
                String message = new String(number);
                String message2 = "connection test" + "\n";
                
                int messagelen = message.length();
                System.out.println("The length of the message is: " + messagelen);
                
                if(message.equals("connection test")) {
                    OutputStream os = socket.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    BufferedWriter bw = new BufferedWriter(osw);
                    String sending = "true";
                    String sending_2 = sending + "\n";
                    bw.write(sending_2);
                    System.out.println("Message sent to client: "+sending_2);
                    bw.flush();
                } else if((messagelen == 32) && (message.equals("connection test") == false) && (message.equals("register") == false)){
                	tobecracked = message;
                	OutputStream os = socket.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    BufferedWriter bw = new BufferedWriter(osw);
                    String sending = "cracking...";
                    String sending_2 = sending + "\n";
                    bw.write(sending_2);
                    System.out.println("Message sent to client: "+ sending_2);
                    bw.flush();
                    cracker(tobecracked);
                } else if(message.equals("register")){
                    OutputStream os = socket.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    BufferedWriter bw = new BufferedWriter(osw);
                    String sending = "Please enter your desired username:";
                    String sending_2 = sending + "\n";
                    bw.write(sending_2);
                    System.out.println("Message sent to client: "+ sending_2);
                    bw.flush();
                } else if(message.equals("waiting")) {
                	while(crackStatus = false) {
                		checkcrackstatus();
                	}
                	OutputStream os = socket.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    BufferedWriter bw = new BufferedWriter(osw);
                    String sending = cracked;
                    String sending_2 = sending + "\n";
                    bw.write(sending_2);
                    System.out.println("Message sent to client: "+ sending_2);
                    bw.flush();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
        
        
    }

    
    	
    public static void checkcrackstatus() {
    	
    }
    
        static String answer="";
        static String cracked;

        public static void cracker(String cracking)
        {
            char ar[]={ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                    's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                    'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3',
                    '4', '5', '6', '7', '8', '9','`','~','!','@','#','$','%','^','&','*','(',')','-','_','=','+',
                    '|','{','}','[',']',';',':',',','<','.','>','/','?'};
            String enc;
            enc=cracking;
            //HERE, 20 denotes the maximum wordlength 20
            final int MAX_WORDLENGTH = 20;//YOU JUST NEED TO CHANGE THIS TO MODIFY THE MAXIMUM WORDLENGTH
            for(int wordlength = 1; wordlength <= MAX_WORDLENGTH; wordlength++)
            {
                if(generate(wordlength,ar,enc))
                {
                	
                	cracked = answer;
                    System.out.print("Match found!! The decrypted string is : "+ answer);
                    crackStatus = true;
                    break;
                }
                else
                {
                    System.out.println("Not a word of "+wordlength+" characters");
                }
            }
        }
        private static boolean generate(int wordlength, char[] alphabet,String enc)
        {
            final long MAX_WORDS = (long) Math.pow(alphabet.length, wordlength);
            final int RADIX = alphabet.length;
            for (long i = 0; i < MAX_WORDS; i++)
            {
                int[] indices = convertToRadix(RADIX, i, wordlength);
                char[] word = new char[wordlength];
                for (int k = 0; k < wordlength; k++)
                {
                    word[k] = alphabet[indices[k]];
                }
                String ss=new String(word);
                if(compareit(encrypt(ss),enc))
                {
                    answer=ss;
                    return true;
                }
            }
            return false;
        }
        private static int[] convertToRadix(int radix, long number, int wordlength)
        {
            int[] indices = new int[wordlength];
            for (int i = wordlength - 1; i >= 0; i--)
            {
                if (number > 0)
                {
                    int rest = (int) (number % radix);
                    number /= radix;
                    indices[i] = rest;
                }
                else
                {
                    indices[i] = 0;
                }

            }
            return indices;
        }
        public static String encrypt(String str)
        {
                byte[] defaultBytes = str.getBytes();
                try
                {
                    MessageDigest algorithm = MessageDigest.getInstance("MD5");
                    algorithm.reset();
                    algorithm.update(defaultBytes);
                    byte messageDigest[] = algorithm.digest();
                    StringBuffer hexString = new StringBuffer();
                    for (int i = 0; i < messageDigest.length; i++)
                    {
                        hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
                    }
                    str = hexString + "";
                } catch(NoSuchAlgorithmException e)
                  {
                      e.printStackTrace();
                  }
                return str;
        }
        public static boolean compareit(String s2, String s1)
        {
            String a=s1;
            if(s1.contains(s2))
                return true;
            else
            {
                /*Java often misses out some zeroes while encrypting text, so here
                 * I'm removing zeroes one by one from the original string and then
                 * performing the check again*/
                while(a.indexOf('0')!=-1)
                {
                    a=a.substring(0,a.indexOf('0'))+a.substring(a.indexOf('0')+1,a.length());
                    if(a.contains(s2))
                        return true;
                }
            }
            return false;
        }
    }