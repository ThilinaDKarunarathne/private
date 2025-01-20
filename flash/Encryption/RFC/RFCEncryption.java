import java.util.*;

public class RFCEncryption {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Input the Plaintext Message : ");
        String plaintext = sc.nextLine();

        System.out.print("Enter the Key Value : ");
        int key = sc.nextInt();
		
		int pl = plaintext.length();
		
		char[][] keymatrix  = new char[key][pl];
		char alphabet;
		
		int d = 0,i = 0;
		
        for (int j = 0; j < pl; j++){
			if(d==0){
				keymatrix[i][j] = plaintext.charAt(j);
				i+=1;
				if(i==(key-1)){
					d = i;
				}
			}else if(d==(key-1)){
				keymatrix[i][j] = plaintext.charAt(j);
				i-=1;
				if(i==0){
					d = i;
				}
			}
		}

		
		// Print pattern
		for (int x = 0; x < key; x++){
            for (int y = 0; y < pl; y++) {
				alphabet = keymatrix[x][y];
				if (alphabet == '\0') {
					System.out.print(' ');
				}
                System.out.print(keymatrix[x][y]);
            }
            System.out.println();
        }

		
		for (int x = 0; x < key; x++) {
			for (int y = 0; y < pl; y++) {
				alphabet = keymatrix[x][y];
				if (alphabet != '\0') {
					System.out.print(alphabet);
				}
			}
		}
    }
}