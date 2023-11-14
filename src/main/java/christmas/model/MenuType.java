package christmas.model;

import java.util.Arrays;

public enum MenuType {
    NONE("없음", 0),
    MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000),
    T_BONE_STEAK("티본스테이크", 55000),
    BARBEQUE_RIP("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000),
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000),
    ZERO_COKE("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String title;
    private final int price;

    MenuType(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public static MenuType findMenuByTitle(String menu) {
        return Arrays.stream(values())
                .filter(menuType -> menuType.title.equals(menu))
                .findFirst()
                .orElse(NONE);

    }

    public boolean isNone() {
        return title.equals(NONE.getTitle());
    }

    public String getTitle() {
        return title;
    }

    public int getPrice(int number) {
        return price * number;
    }
}
