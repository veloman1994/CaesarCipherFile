public class CaesarCipherBruteForce extends CaesarCipherFile {


    public static char[] getDoneChar() {
        return doneChar;
    }

    public static void setDoneChar(char[] doneChar) {
        CaesarCipherBruteForce.doneChar = doneChar;
    }

    private static char[]doneChar;

    public static char[] bruteForceDecryptWithDictionary(char[] text) {
        char[] decryptedChar = new char[text.length];
        for (int shift = 0; shift < text.length; shift++) {
            decryptedChar = encrypt(text, shift);
            StringBuilder decryptedText = new StringBuilder();
            int numLetters = 0;
            int numSpaces = 0;
            int notLetter =0;

            for (char ch : decryptedChar) {
                if (Character.isLetter(ch)) {
                    numLetters++;
                } else if (ch == '.' || ch == ',' || ch == '”' || ch == ':' || ch == '-' || ch == '!' || ch == '?') {
                    notLetter++;
                } else if (Character.isWhitespace(ch)) {
                    numSpaces++;
                }
            }
            if ((numLetters+ numLetters) / numSpaces > 3 && (numLetters + notLetter) / numSpaces < 8) {
                    break;
            }
        }
        return decryptedChar;
    }
}
