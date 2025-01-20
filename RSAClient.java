import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Scanner;
public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 12345;
	private static int C=0;

    public static void main(String[] args) throws Exception {
		Scanner obj = new Scanner(System.in);
		BigInteger encryptedMessage;
		String decryptedMessage;
        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Connected to server.");
		
            // Receive public key from the server
            BigInteger e = (BigInteger) in.readObject();
            BigInteger n = (BigInteger) in.readObject();
            System.out.println("Public key received: (e = " + e + ", n = " + n + ")");
		
			while( C != 3){
			
			System.out.println("Choose Option : \n \t 1. Encrypt \n\t 2. Decrypt \n \t 3. Exit");
			C = obj.nextInt();
			obj.nextLine();
			out.writeObject(C);
			if(C==1){
				// Message to send
				System.out.println("Enter Text to Encrypt: ");
				String message = obj.nextLine();
				
				out.writeObject(message);
				encryptedMessage = (BigInteger) in.readObject();
				System.out.println("\nEncrypted message: " + encryptedMessage+"\n\n");
			}
           
			if(C ==2){
				System.out.println("\n\nEnter Text to Decrypt: ");
				BigInteger messageToDecrypt = obj.nextBigInteger();
				obj.nextLine();
				
				out.writeObject(messageToDecrypt);
				decryptedMessage = (String) in.readObject();
				System.out.println("\nDecrypted message: " + decryptedMessage+"\n\n");
							
			}

            // Receive response from the server
            String response = (String) in.readObject();
            System.out.println("Response from server: " + response);
			}
        }
    }
}
