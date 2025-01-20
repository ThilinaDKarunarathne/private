import java.util.*;

public class RFCDecryption {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Input the Ciphertext Message : ");
        String ciphertext = sc.nextLine();

        System.out.print("Enter the Key Value : ");
        int key = sc.nextInt();

        int pl = ciphertext.length();

        char[][] keymatrix = new char[key][pl];
        char alphabet;

        int direction = 0;
        int row = 0, col = 0;

        for(int i = 0; i < pl; i++) {
            if(row == 0){
                direction = 0; 
            }else if(row == key - 1){
                direction = 1; 
            }

            keymatrix[row][col++] = '*';

            if(direction == 0){
                row++;
            }else{
                row--;
            }
        }

        int index = 0;
        for (int i = 0; i < key; i++) {
            for(int j = 0; j < pl; j++){
                if (keymatrix[i][j] == '*' && index < pl) {
                    keymatrix[i][j] = ciphertext.charAt(index++);
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

        direction = 0;
        row = 0;
        col = 0;
        for(int i = 0; i < pl; i++) {
            if(row == 0){
                direction = 0;
            }else if(row == key - 1){
                direction = 1; 
            }

            alphabet = keymatrix[row][col++];
            System.out.print(alphabet);

            if(direction == 0){
                row++;
            }else{
                row--;
            }
        }
    }
}