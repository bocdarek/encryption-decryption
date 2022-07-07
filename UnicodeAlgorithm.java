package encryptdecrypt;


public class UnicodeAlgorithm implements  Algorithm {

    @Override
    public String convert(String input, int shift, String mode) {
        if (mode.equals("enc")) {
            return shiftCharacters(input, shift);
        } else if (mode.equals("dec")) {
            return shiftCharacters(input, -shift);
        } else {
            System.out.println("Error! Invalid mode.");
            System.exit(0);
            return "";
        }
    }

    private String shiftCharacters(String input, int shift) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int index = input.charAt(i) + shift;
            encryptedMessage.append((char) index);
        }
        return encryptedMessage.toString();
    }
}

