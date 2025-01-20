import java.util.*;

public class Decrypt{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input the Cipher Text Message : ");
		String ciphertext = sc.nextLine();
		
		System.out.print("Enter Shift Value : ");
		int shift = sc.nextInt();
		
		String plaintext = "";
		
		char alphabet;
		for(int i = 0; i< ciphertext.length();i++){
			alphabet = ciphertext.charAt(i);
			
			if(alphabet >= 'a' && alphabet <= 'z'){
				alphabet = (char)(alphabet - shift);
				if(alphabet < 'a'){
					alphabet = (char)(alphabet - ('z'-'a' + 1));
				}
				plaintext += alphabet;
			}
			else if(alphabet >= 'A' && alphabet <= 'Z'){
				alphabet = (char)(alphabet - shift);
				if(alphabet < 'A'){
					alphabet = (char)(alphabet - ('Z'-'A' + 1));
				}
				plaintext += alphabet;
			}

		}
		System.out.println("Plain text : " + plaintext);
	}
}