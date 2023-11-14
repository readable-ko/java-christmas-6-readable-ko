package christmas.model;

public class Customer {
    private final Date date;
    private final Menu menu;
    private final Money money;
    private final Badge badge;

    public Customer(Date date, Menu menu, Money money) {
        this.date = date;
        this.menu = menu;
        this.money = money;
        this.badge = Badge.getBadge(money.getTotalDiscountPrice());
    }

    public Date getDate() {
        return date;
    }

    public Menu getMenu() {
        return menu;
    }

    public Money getMoney() {
        return money;
    }

    public Badge getBadge() {
        return badge;
    }
}
