package encryptdecrypt;

import java.util.HashMap;
import java.util.MissingFormatArgumentException;

public class Main {
    public static void main(String[] args) {

        HashMap<String, String> arguments = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            String key = args[i];
            String value;
            try {
                if (!key.startsWith("-")) {
                    throw new MissingFormatArgumentException("");
                }
                value = args[i + 1];
            } catch (Exception e) {
                System.out.println("Error! Wrong set of parameters.");
                return;
            }
            arguments.put(key, value);
        }

        EncryptionInterface ei = new EncryptionInterface(arguments);
        ei.makeConversion();
    }
}
