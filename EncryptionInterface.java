package encryptdecrypt;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class EncryptionInterface {

    private Algorithm algorithm;

    private final String mode;
    private final int key;
    private final String data;
    private final String out;
    private final String in;
    private final String alg;

    public EncryptionInterface(HashMap<String, String> dict) {
        this.mode = dict.getOrDefault("-mode", "enc");
        this.key = Integer.parseInt(dict.getOrDefault("-key", "0"));
        this.data = dict.getOrDefault("-data", "");
        this.out = dict.getOrDefault("-out", null);
        this.in = dict.getOrDefault("-in", null);
        this.alg = dict.getOrDefault("-alg", "shift");
        assignAlgorithm();
    }

    private void assignAlgorithm() {
        if (alg.equalsIgnoreCase("shift")) {
            algorithm = new ShiftAlgorithm();
        } else if (alg.equalsIgnoreCase("unicode")) {
            algorithm = new UnicodeAlgorithm();
        } else {
            System.out.println("Error! Invalid algorithm.");
            System.exit(0);
        }
    }

    public void makeConversion() {
        String convertedData;
        if (in == null || !data.isEmpty()) {
            convertedData = convertData();
        } else {
            convertedData = convertFromFile();
        }
        outputData(convertedData);
    }

    private String convertFromFile() {
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader(in)) {
            int i;
            while ((i = fr.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException e) {
            System.out.println("Error! Reading from file unsuccessful.");
            System.exit(0);
        }
        return algorithm.convert(sb.toString(), key, mode);
    }

    private String convertData() {
        return algorithm.convert(data, key, mode);
    }

    private void outputData(String convertedData) {
        if (out == null) {
            System.out.println(convertedData);
        } else {
            try (PrintWriter pw = new PrintWriter(out)) {
                pw.println(convertedData);
            } catch (IOException e) {
                System.out.println("Error! Writing to file unsuccessful." );
            }
        }
    }
}

