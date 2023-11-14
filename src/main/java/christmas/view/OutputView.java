package christmas.view;

public class OutputView {
    private static final String MONEY_FORMAT = "%,d원\n";

    public static void println(String message) {
        System.out.println(message);
    }

    public static void println(int money) {
        System.out.printf(MONEY_FORMAT, money);
    }
    
    public static void print(String message) {
        System.out.println(message);
    }
}
