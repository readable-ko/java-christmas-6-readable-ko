package christmas.controller;

import static christmas.utils.Message.*;

import christmas.model.Badge;
import christmas.model.Customer;
import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.MenuType;
import christmas.model.Money;
import christmas.model.Sale;
import christmas.model.SaleType;
import christmas.view.OutputView;

public class OutputViewController {
    public static void printOrderInfo(Customer customer) {
        printPreview(customer.getDate());
        printOrderedMenu(customer.getMenu());
        printAmountInfo(customer.getMoney());
        printBadge(customer.getBadge());
    }

    private static void printPreview(Date date) {
        OutputView.println(String.format(AFTER_DATE_MESSAGE, date.getDay()));
    }

    private static void printOrderedMenu(Menu menu) {
        OutputView.println(AFTER_MENU_MESSAGE);
        menu.getOrderedMenu().forEach(
                (order, count) -> OutputView.println(String.format("%s %d개", order.getTitle(), count)));
    }

    private static void printAmountInfo(Money money) {
        OutputView.println(ORDER_AMOUNT_MESSAGE);
        OutputView.println(money.getTotalOrderPrice());

        printGiveAway(money.getGiveAway());
        printBenefitInfo(money);
    }

    private static void printGiveAway(MenuType menuType) {
        String message = menuType.getTitle();
        if (menuType == MenuType.CHAMPAGNE) {
            message += ONE_GIVE_AWAY;
        }
        OutputView.println(GIVE_AWAY_MESSAGE);
        OutputView.println(message);
    }

    private static void printBenefitInfo(Money money) {
        OutputView.println(DISCOUNT_HISTORY);
        money.getSaleResults().forEach(OutputViewController::printSaleResult);

        printTotalDiscount(money);
        printCharge(money);
    }

    private static void printSaleResult(SaleType saleType, int price) {
        OutputView.print(String.format(DISCOUNT_MESSAGE, saleType.toString()));
        if (saleType != SaleType.NONE) {
            OutputView.print(" ");
            OutputView.println(-price);
        }
    }

    private static void printTotalDiscount(Money money) {
        OutputView.print(TOTAL_DISCOUNT);
        OutputView.println(-money.getTotalDiscountPrice());
    }

    private static void printCharge(Money money) {
        OutputView.println(TOTAL_CHARGE_AMOUNT);
        OutputView.println(money.getTotalChargePrice());
    }

    private static void printBadge(Badge badge) {
        OutputView.println(EVENT_BADGE);
        OutputView.print(badge.getTitle());
    }
}
