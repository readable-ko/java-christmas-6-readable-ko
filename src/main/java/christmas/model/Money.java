package christmas.model;

import static christmas.utils.MagicNumber.ONE;
import static christmas.utils.MagicNumber.ZERO;
import static christmas.utils.Message.DISCOUNT_MESSAGE;

import christmas.view.OutputView;
import java.util.EnumMap;

public class Money {
    private final int totalOrderPrice;
    private final int totalDiscountPrice;
    private final EnumMap<SaleType, Integer> saleResults;

    private final MenuType giveAway;

    public Money(int totalOrderPrice, EnumMap<SaleType, Integer> saleResults) {
        this.totalOrderPrice = totalOrderPrice;
        this.saleResults = saleResults;
        this.totalDiscountPrice = sumSaleResults();
        this.giveAway = checkGiveAway();
    }

    private int sumSaleResults() {
        if (!saleResults.containsKey(SaleType.NONE)) {
            return saleResults.values().stream().mapToInt(Integer::intValue).sum();
        }
        return ZERO;
    }

    private MenuType checkGiveAway() {
        if (saleResults.containsKey(SaleType.GIVE_AWAY_EVENT)) {
            return MenuType.CHAMPAGNE;
        }
        return MenuType.NONE;
    }

    public int getTotalChargePrice() {
        if (giveAway == MenuType.CHAMPAGNE) {
            return totalOrderPrice - totalDiscountPrice + giveAway.getPrice(ONE);
        }
        return totalOrderPrice - totalDiscountPrice;
    }

    public EnumMap<SaleType, Integer> getSaleResults() {
        return saleResults;
    }

    public MenuType getGiveAway() {
        return giveAway;
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public int getTotalDiscountPrice() {
        return totalDiscountPrice;
    }
}
