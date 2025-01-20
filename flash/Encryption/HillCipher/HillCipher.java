import java.util.*;

public class HillCipher {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Input the Keyword : ");
        String keyword = sc.nextLine();
		
		System.out.print("Input the Plaintext : ");
        String plaintext = sc.nextLine();
		
		int[][] keymatrix  = new int[3][3];
		int index = 0;
		
		String ciphertext = "";
		char alphabet;
		int  p = 0;
		int charvalue = 0;
		int pvalue = 0;
		int pvalueMod = 0;
		
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keymatrix[i][j] = (keyword.charAt(index)) % 97;
                index++;
            }
        }
		
	    for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(keymatrix[i][j] + " ");
            }
            System.out.println();
        }
		

		for (int i = 0; i < plaintext.length(); i++) {
			pvalueMod = 0;
			pvalue = 0;
			for (int j = 0; j < plaintext.length(); j++) {
				alphabet = (plaintext.charAt(j)); 
				charvalue = (int)(alphabet)% 97;
				pvalue += charvalue*keymatrix[i][j];
			}
			pvalueMod = (pvalue % 26) + 97;
			ciphertext += (char)pvalueMod;
        }
		System.out.println(ciphertext);
    }
}
