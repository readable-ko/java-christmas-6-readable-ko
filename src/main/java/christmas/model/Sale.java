package christmas.model;

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

        Arrays.stream(SaleType.values())
                .filter(saleType -> saleType.applySale(date, menu) > ZERO)
                .forEach(saleType -> saleTypes.put(saleType, saleType.applySale(date, menu)));

        if (saleTypes.isEmpty()) {
            saleTypes.put(SaleType.NONE, 0);
        }
        return saleTypes;
    }

    public Money getMoney() {
        return money;
    }
}
