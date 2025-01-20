import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class RSA_Client {
    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Connected to server.");

            // Receive public key from the server
            BigInteger e = (BigInteger) in.readObject();
            BigInteger n = (BigInteger) in.readObject();
            System.out.println("Public key received: (e = " + e + ", n = " + n + ")");

            // Message to send
            String message = "Hi";
            System.out.println("Original message: " + message);

            // Encrypt the message
            BigInteger plaintext = new BigInteger(message.getBytes());
            BigInteger encryptedMessage = plaintext.modPow(e, n);
            System.out.println("Encrypted message: " + encryptedMessage);

            // Send encrypted message to the server
            out.writeObject(encryptedMessage);

            // Receive response from the server
            String response = (String) in.readObject();
            System.out.println("Response from server: " + response);
        }
    }
}