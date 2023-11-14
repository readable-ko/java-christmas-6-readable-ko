package christmas.model;

import java.util.Arrays;
import java.util.function.Function;

public enum Badge {
    NONE("없음", value -> value < 5000),
    STAR("별", value -> value >= 5000 && value < 10000),
    TREE("트리", value -> value >= 10000 && value < 20000),
    SANTA("산타", value -> value >= 20000);

    private String title;
    private Function<Integer, Boolean> expression;

    Badge(String title, Function<Integer, Boolean> expression) {
        this.title = title;
        this.expression = expression;
    }

    public static Badge getBadge(int amount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.getExpression(amount))
                .findAny()
                .orElse(NONE);
    }

    public String getTitle() {
        return title;
    }

    public boolean getExpression(int amount) {
        return expression.apply(amount);
    }
}
