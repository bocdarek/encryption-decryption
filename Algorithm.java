package encryptdecrypt;

public interface Algorithm {

    public abstract String convert(String input, int shift, String mode);
}
