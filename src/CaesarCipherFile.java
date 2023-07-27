import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class CaesarCipherFile {
    public static   char[] upperAlphabet = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ.,”:-!? ".toCharArray();
    public static   char[] lowerAlphabet = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя.,”:-!? ".toCharArray();
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть шлях до фалу:");
        String fileInput = scanner.nextLine();

        System.out.println("Веведіть шлях для збереження нового файлу:");
        String fileOutput = scanner.nextLine();
        try {
            char[] textChar = readTextFromFile(fileInput);
            if (textChar != null) {
                System.out.println("Введіть ціле число більше за 0 для зсуву:");
                int shift = scanner.nextInt();
                System.out.println("Введіть число 1 для зсуву вправо, число 2 для зсуву вліво:");
                int way = scanner.nextInt();
                System.out.println("Введіть число 1 щоб зашифрувати чило, число 2 щоб розшифрувати:");
                int cipher = scanner.nextInt();

                if (way == 1 && cipher == 1 || way == 1 && cipher == 2) {
                    char[] encryptedChar = encrypt(textChar, shift);
                    saveEncryptedTextToFile(fileOutput, encryptedChar);
                } else if (way == 2 && cipher == 2 || way == 2 && cipher == 1) {
                    char[] decryptedChar = decrypt(textChar, shift);
                    saveEncryptedTextToFile(fileOutput, decryptedChar);
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка введення/виведення: " + e.getMessage());
        }
    }
    public static char[] readTextFromFile(String fileInput) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(fileInput));
        return new String(bytes, StandardCharsets.UTF_8).toCharArray();
    }
    public static char[] encrypt(char[] text, int step) {

        char[] encrypted = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            int index = indexOf(upperAlphabet, (text[i]));
            int result = (index + step) % upperAlphabet.length;

                if (index == -1) {
                encrypted[i] = text[i];
                }
                     else if (Character.isLowerCase(text[i]) || Character.isWhitespace(text[i]) ||
                            text[i] == '.' || text[i] == ',' || text[i] == '”' || text[i] == ':' || text[i] == '-' || text[i] == '!' || text[i] == '?') {
                    encrypted[i] = lowerAlphabet[result];
                }else if (Character.isUpperCase(text[i]) || Character.isWhitespace(text[i]) ||
                            text[i] == '.' || text[i] == ',' || text[i] == '”' || text[i] == ':' || text[i] == '-' || text[i] == '!' || text[i] == '?') {
                        encrypted[i] = upperAlphabet[result];
                    }
        }
        return encrypted;
    }
    public static char[] decrypt(char[] text, int step2) {
         step2 = lowerAlphabet.length - step2;
        return encrypt(text, step2);

    }

    private static int indexOf(char[] upperAlphabet, char c) {
        for (int i = 0; i < upperAlphabet.length; i++) {
            if (upperAlphabet[i] == Character.toUpperCase(c)) {
                return i;
            }
        }
       return -1;
    }

    public static void saveEncryptedTextToFile(String filePath, char[] encryptedChars) throws IOException {
       String encryptedText = new String(encryptedChars);
       Files.write(Paths.get(filePath), encryptedText.getBytes(StandardCharsets.UTF_8));
       System.out.println("Зашифрований текст був збережений у файл: " + filePath);
    }
}








