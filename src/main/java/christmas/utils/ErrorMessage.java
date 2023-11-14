package christmas.utils;

public class ErrorMessage {
    private static final String PREFIX = "[ERROR] ";
    public static final String IS_NOT_INTEGER_DATE = PREFIX + "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String IS_NOT_DEFINED_MENU = PREFIX + "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String DRINK_ONLY_NOT_ALLOWED = PREFIX + "음료만 주문 시, 주문할 수 없습니다.";
    public static final String IS_MORE_THAN_MAXIMUM = PREFIX + "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.";
}
