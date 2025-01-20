import java.util.*;

public class OTPDecrypt {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Input the Ciphertext Message : ");
        String ciphertext = sc.nextLine();

        System.out.print("Enter the Key Text : ");
        String key = sc.nextLine();
		
		int charvalue = 0, cvalue = 0;
		
		if(key.length() == ciphertext.length()){
			for(int i = 0; i < ciphertext.length(); i++){
				if(ciphertext.charAt(i) >= 'a' && ciphertext.charAt(i) <= 'z'){
					charvalue = (int)(ciphertext.charAt(i))% 97 - (int)(key.charAt(i))% 65;
					if(charvalue < 0){
						charvalue += 26;
					}
					cvalue = ((charvalue)%26) + 97;
					
				}else if(ciphertext.charAt(i) >= 'A' && ciphertext.charAt(i) <= 'Z'){
					charvalue = (int)(ciphertext.charAt(i))% 65 - (int)(key.charAt(i))% 65;
					if(charvalue < 0){
						charvalue += 26;
					}
					cvalue = ((charvalue)%26) + 65;
					
				}
				System.out.print((char)cvalue);
			}
		}else{
			System.out.print("Plaintext And Key Text Length Must be Same");
		}
    }
}
