import java.util.Scanner;

public class PlayfairCipher {
    static int SIZE = 30;

    static void toLowerCase(char[] plain, int ps) {
        for (int i = 0; i < ps; i++) {
            if (plain[i] >= 'A' && plain[i] <= 'Z')
                plain[i] += 32;
        }
    }

    static int removeSpaces(char[] plain, int ps) {
        int count = 0;
        for (int i = 0; i < ps; i++) {
            if (plain[i] != ' ')
                plain[count++] = plain[i];
        }
        return count;
    }

    static void generateKeyTable(char[] key, int ks, char[][] keyT) {
        boolean[] dict = new boolean[26];
        int i = 0, j = 0;

        for (int k = 0; k < ks; k++) {
            if (key[k] != 'j' && !dict[key[k] - 'a']) {
                dict[key[k] - 'a'] = true;
                keyT[i][j++] = key[k];
                if (j == 5) {
                    i++;
                    j = 0;
                }
            }
        }

        for (int k = 0; k < 26; k++) {
            if (!dict[k] && (char) (k + 'a') != 'j') {
                keyT[i][j++] = (char) (k + 'a');
                if (j == 5) {
                    i++;
                    j = 0;
                }
            }
        }
    }

    static void search(char[][] keyT, char a, char b, int[] pos) {
        if (a == 'j') a = 'i';
        if (b == 'j') b = 'i';

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyT[i][j] == a) {
                    pos[0] = i;
                    pos[1] = j;
                } else if (keyT[i][j] == b) {
                    pos[2] = i;
                    pos[3] = j;
                }
            }
        }
    }

    static int mod5(int a) {
        return (a + 5) % 5;
    }

    static int prepare(char[] str, int len) {
        for (int i = 0; i < len; i += 2) {
            if (i + 1 == len || str[i] == str[i + 1]) {
                len++;
                for (int j = len; j > i + 1; j--) {
                    str[j] = str[j - 1];
                }
                str[i + 1] = 'x';
            }
        }
        if (len % 2 != 0) {
            str[len++] = 'z';
        }
        return len;
    }

    static void encrypt(char[] str, char[][] keyT, int ps) {
        for (int i = 0; i < ps; i += 2) {
            int[] pos = new int[4];
            search(keyT, str[i], str[i + 1], pos);

            if (pos[0] == pos[2]) {
                str[i] = keyT[pos[0]][mod5(pos[1] + 1)];
                str[i + 1] = keyT[pos[0]][mod5(pos[3] + 1)];
            } else if (pos[1] == pos[3]) {
                str[i] = keyT[mod5(pos[0] + 1)][pos[1]];
                str[i + 1] = keyT[mod5(pos[2] + 1)][pos[1]];
            } else {
                str[i] = keyT[pos[0]][pos[3]];
                str[i + 1] = keyT[pos[2]][pos[1]];
            }
        }
    }

    static void decrypt(char[] str, char[][] keyT, int ps) {
        for (int i = 0; i < ps; i += 2) {
            int[] pos = new int[4];
            search(keyT, str[i], str[i + 1], pos);

            if (pos[0] == pos[2]) {
                str[i] = keyT[pos[0]][mod5(pos[1] - 1)];
                str[i + 1] = keyT[pos[0]][mod5(pos[3] - 1)];
            } else if (pos[1] == pos[3]) {
                str[i] = keyT[mod5(pos[0] - 1)][pos[1]];
                str[i + 1] = keyT[mod5(pos[2] - 1)][pos[1]];
            } else {
                str[i] = keyT[pos[0]][pos[3]];
                str[i + 1] = keyT[pos[2]][pos[1]];
            }
        }
    }

    static String removePadding(String decryptedText) {
        return decryptedText.replaceAll("x$", "").replaceAll("x(?![a-z])", "");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] str = new char[SIZE];
        char[] key = new char[SIZE];

        while (true) {
            System.out.println("\nChoose option: \n1. Encryption \n2. Decryption \n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter Key text: ");
            String keyInput = sc.nextLine().replaceAll(" ", "").toLowerCase();
            strcpy(key, keyInput);

            System.out.print("Enter text: ");
            String text = sc.nextLine().toLowerCase();
            strcpy(str, text);

            char[][] keyT = new char[5][5];
            generateKeyTable(key, keyInput.length(), keyT);

            if (choice == 1) {
                int ps = prepare(str, text.length());
                encrypt(str, keyT, ps);
                System.out.println("Cipher text: " + String.valueOf(str, 0, ps));
            } else if (choice == 2) {
                decrypt(str, keyT, text.length());
                String decipheredText = removePadding(String.valueOf(str).trim());
                System.out.println("Deciphered text: " + decipheredText);
            }
        }

        sc.close();
    }

    static void strcpy(char[] arr, String s) {
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }
    }
}
