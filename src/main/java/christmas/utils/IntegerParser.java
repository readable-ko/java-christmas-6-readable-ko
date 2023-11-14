package christmas.utils;

public class IntegerParser {
    private IntegerParser() {

    }

    public static int convert(String value, String errorMessage) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
