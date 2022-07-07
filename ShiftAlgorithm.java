package encryptdecrypt;

public class ShiftAlgorithm implements  Algorithm {

    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private final String alphabetInUpper = alphabet.toUpperCase();
    private final int length = alphabet.length();

    @Override
    public String convert(String input, int shift, String mode) {
        if (mode.equals("enc")) {
            return shiftCharacters(input, shift, false);
        } else if (mode.equals("dec")) {
            return shiftCharacters(input, shift, true);
        } else {
            System.exit(0);
            return "";
        }

    }

    private String shiftCharacters(String input, int shift, boolean reversed) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (alphabet.contains(ch + "")) {
                ch = makeShift(alphabet, ch, shift, reversed);
            } else if (alphabetInUpper.contains(ch + "")) {
                ch = makeShift((alphabetInUpper), ch, shift, reversed);
            }
            encryptedMessage.append(ch);
        }
        return encryptedMessage.toString();
    }

    private char makeShift(String letters, char ch, int shift, boolean reversed) {
        if (reversed) {
            letters = new StringBuilder(letters).reverse().toString();
        }
        int index = letters.indexOf(ch);
        int newIndex = (index + shift) % length;
        return letters.charAt(newIndex);
    }
}



