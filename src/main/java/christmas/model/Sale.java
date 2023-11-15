package christmas.model;

import static christmas.utils.MagicNumber.MIN_DISCOUNT_AMOUNT;
import static christmas.utils.MagicNumber.ZERO;

import java.util.Arrays;
import java.util.EnumMap;

public class Sale {
    private final Menu menu;
    private final Date date;
    private final Money money;

    public Sale(Date date, Menu menu) {
        this.menu = menu;
        this.date = date;
        int totalPrice = menu.getTotalPrice();
        EnumMap<SaleType, Integer> discountPrice = checkSale(totalPrice);

        this.money = new Money(totalPrice, discountPrice);
    }

    public EnumMap<SaleType, Integer> checkSale(int totalPrice) {
        EnumMap<SaleType, Integer> saleTypes = new EnumMap<>(SaleType.class);
        if (isNotSaleCondition(totalPrice)) {
            saleTypes.put(SaleType.NONE, 0);
            return saleTypes;
        }

        Arrays.stream(SaleType.values())
                .filter(saleType -> saleType.applySale(date, menu) > ZERO)
                .forEach(saleType -> saleTypes.put(saleType, saleType.applySale(date, menu)));

        if (saleTypes.isEmpty()) {
            saleTypes.put(SaleType.NONE, 0);
        }
        return saleTypes;
    }

    private boolean isNotSaleCondition(int totalPrice) {
        return totalPrice < MIN_DISCOUNT_AMOUNT;
    }

    public Money getMoney() {
        return money;
    }
}
