import java.util.*;

public class HillCipherDecrypt {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Input the Keyword : ");
        String keyword = sc.nextLine();

        System.out.print("Input the Ciphertext : ");
        String ciphertext = sc.nextLine();

        int[][] keymatrix = new int[3][3];
        int index = 0;

        // Calculate the modular multiplicative inverse of the determinant of the key matrix
        int det = ((keyword.charAt(0) - 'a') * ((keyword.charAt(4) - 'a') * (keyword.charAt(8) - 'a') - (keyword.charAt(5) - 'a') * (keyword.charAt(7) - 'a'))
                - (keyword.charAt(1) - 'a') * ((keyword.charAt(3) - 'a') * (keyword.charAt(8) - 'a') - (keyword.charAt(5) - 'a') * (keyword.charAt(6) - 'a'))
                + (keyword.charAt(2) - 'a') * ((keyword.charAt(3) - 'a') * (keyword.charAt(7) - 'a') - (keyword.charAt(4) - 'a') * (keyword.charAt(6) - 'a')));
        int detInv = 0;
        for (int i = 1; i < 26; i++) {
            if ((det * i) % 26 == 1) {
                detInv = i;
                break;
            }
        }

        // Calculate the adjugate of the key matrix
        int[][] adjugate = new int[3][3];
        adjugate[0][0] = ((keyword.charAt(4) - 'a') * (keyword.charAt(8) - 'a') - (keyword.charAt(5) - 'a') * (keyword.charAt(7) - 'a')) % 26;
        adjugate[0][1] = -((keyword.charAt(3) - 'a') * (keyword.charAt(8) - 'a') - (keyword.charAt(5) - 'a') * (keyword.charAt(6) - 'a')) % 26;
        adjugate[0][2] = ((keyword.charAt(3) - 'a') * (keyword.charAt(7) - 'a') - (keyword.charAt(4) - 'a') * (keyword.charAt(6) - 'a')) % 26;
        adjugate[1][0] = -((keyword.charAt(1) - 'a') * (keyword.charAt(8) - 'a') - (keyword.charAt(2) - 'a') * (keyword.charAt(7) - 'a')) % 26;
        adjugate[1][1] = ((keyword.charAt(0) - 'a') * (keyword.charAt(8) - 'a') - (keyword.charAt(2) - 'a') * (keyword.charAt(6) - 'a')) % 26;
        adjugate[1][2] = -((keyword.charAt(0) - 'a') * (keyword.charAt(7) - 'a') - (keyword.charAt(1) - 'a') * (keyword.charAt(6) - 'a')) % 26;
        adjugate[2][0] = ((keyword.charAt(1) - 'a') * (keyword.charAt(5) - 'a') - (keyword.charAt(2) - 'a') * (keyword.charAt(4) - 'a')) % 26;
        adjugate[2][1] = -((keyword.charAt(0) - 'a') * (keyword.charAt(5) - 'a') - (keyword.charAt(2) - 'a') * (keyword.charAt(3) - 'a')) % 26;
        adjugate[2][2] = ((keyword.charAt(0) - 'a') * (keyword.charAt(4) - 'a') - (keyword.charAt(1) - 'a') * (keyword.charAt(3) - 'a')) % 26;

        // Adjust negative values in the adjugate
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (adjugate[i][j] < 0) {
                    adjugate[i][j] += 26;
                }
            }
        }

        // Calculate the inverse of the key matrix using the determinant inverse
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keymatrix[i][j] = (adjugate[i][j] * detInv) % 26;
            }
        }

        String plaintext = "";

        // Decrypt the ciphertext
        for (int i = 0; i < ciphertext.length(); i += 3) {
            int[] cipherVector = new int[3];
            for (int j = 0; j < 3; j++) {
                cipherVector[j] = (ciphertext.charAt(i + j) - 'a') % 26;
            }
            int[] plainVector = new int[3];
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    plainVector[j] += (keymatrix[j][k] * cipherVector[k]) % 26;
                }
                plainVector[j] = plainVector[j] % 26;
            }
            for (int j = 0; j < 3; j++) {
                plaintext += (char) (plainVector[j] + 'a');
            }
        }

        System.out.println("Decrypted plaintext: " + plaintext);
    }
}
