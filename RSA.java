import java.io.*;
import java.security.SecureRandom;
import java.math.*;

public class RSA{
	
	private static BigInteger e,d,n;
	
	public static void main(String[] args){
		
		int bitLen = 1024;
		
		SecureRandom random = new SecureRandom();
		
		BigInteger p = new BigInteger(bitLen/2,100,random);
		BigInteger q = new BigInteger(bitLen/2,100,random);
		
		n= p.multiply(q);
		
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		
		e = new BigInteger("65537");
		d = e.modInverse(phi);
		
		
		String mess = "Hello, World!";
		
		BigInteger enc =  new BigInteger(mess.getBytes());
		BigInteger encrypted = enc.modPow(e,n);
		System.out.println(encrypted);
		
		BigInteger dec = encrypted.modPow(d,n);
		String decrypted = new String(dec.toByteArray());
		System.out.println(decrypted);
		
		
		
	}
	
	
}