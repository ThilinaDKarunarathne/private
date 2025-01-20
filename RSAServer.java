import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.SecureRandom;
import java.util.Scanner;

public class Server {
    private static final int PORT = 12345;

    private static BigInteger n, d, e;
	
	private static int C=0;
	
	
    public static void main(String[] args) throws Exception {
        Scanner obj = new Scanner(System.in);
		// Generate RSA keys
        int bitLength = 1024;
        SecureRandom random = new SecureRandom();
        BigInteger p = new BigInteger(bitLength / 2, 100, random);
        BigInteger q = new BigInteger(bitLength / 2, 100, random);
        n = p.multiply(q); // n = p x q
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); //(p-1)x(q-1)
        e = new BigInteger("65537"); // Common choice for e
        d = e.modInverse(phi);

        System.out.println("Server is running...");
        System.out.println("Public Key: (e = " + e + ", n = " + n + ")");
        System.out.println("Private Key: (d = " + d + ", n = " + n + ")");

        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket socket = serverSocket.accept();
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Client connected.");

            // Send public key to the client
            out.writeObject(e);
            out.writeObject(n);
            out.flush();
	
			
			while(C != 3){
			C = (int) in.readObject();
			
			if(C == 1){
				// Encrypt the message
				String message =(String) in.readObject();
				System.out.println("\n\nmessage: " + message);
				BigInteger plaintext = new BigInteger(message.getBytes());
				BigInteger encryptedMessage = plaintext.modPow(e, n);
				System.out.println("\n\nEncrypted message: " + encryptedMessage);
				out.writeObject(encryptedMessage);
			}
			
			if( C == 2){
				// Receive encrypted message from the client
				BigInteger messageToDecrypt = (BigInteger) in.readObject();
				System.out.println("\n\nEncrypted message received: " + messageToDecrypt);
				
				// Decrypt the message
				BigInteger decryptedMessage = messageToDecrypt.modPow(d, n);
				String message = new String(decryptedMessage.toByteArray());
				System.out.println("\nDecrypted message: " + message+"\n\n");
				out.writeObject(message);
				
			}

            // Respond to the client
            String response = "Done ! ";
            out.writeObject(response);
			}
        }
    }
}