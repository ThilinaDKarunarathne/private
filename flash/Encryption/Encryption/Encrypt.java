import java.util.*;

public class Encrypt{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input the Plain Text Message : ");
		String plainText = sc.nextLine();
		
		System.out.print("Enter Shift Value : ");
		int shift = sc.nextInt();
		
		String cipherText = "";
		
		char alphabet;
		for(int i = 0; i< plainText.length();i++){
			alphabet = plainText.charAt(i);
			
			if(alphabet >= 'a' && alphabet <= 'z'){
				alphabet = (char)(alphabet + shift);
				if(alphabet>'z'){
					alphabet = (char)(alphabet - ('z'-'a'+1));
				}
				cipherText += alphabet;
			}
			else if(alphabet >= 'A' && alphabet <= 'Z'){
				alphabet = (char)(alphabet + shift);
				if(alphabet > 'Z'){
					alphabet = (char)(alphabet - ('Z'-'A'+1));
				}
				cipherText += alphabet;
			}
			else{
				cipherText += alphabet;
			}
		}
		System.out.println("Cipher Text : " + cipherText);
	}
}