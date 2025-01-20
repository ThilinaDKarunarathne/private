import java.util.*;

public class OTPEncrypt {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Input the plaintext message : ");
        String plaintext = sc.nextLine();

        System.out.print("Enter the Key text : ");
        String key = sc.nextLine();
		
		int charvalue = 0, cvalue = 0;
		
		if(key.length() == plaintext.length()){
			for(int i = 0; i < plaintext.length(); i++){
				if(plaintext.charAt(i) >= 'a' && plaintext.charAt(i) <= 'z'){
					charvalue = (int)(plaintext.charAt(i))% 97 + (int)(key.charAt(i))% 65;
					cvalue = ((charvalue)%26) + 97;
				}else if(plaintext.charAt(i) >= 'A' && plaintext.charAt(i) <= 'Z'){
					charvalue = (int)(plaintext.charAt(i))% 65 + (int)(key.charAt(i))% 65;
					cvalue = ((charvalue)%26) + 65;
				}
				System.out.print((char)cvalue);
			}
		}else{
			System.out.print("Plaintext And Key Text Length Must be Same");
		}
    }
}
