package christmas.controller;

import static christmas.controller.InputViewController.read;
import static christmas.controller.OutputViewController.printOrderInfo;
import static christmas.utils.Message.ASK_DATE_MESSAGE;
import static christmas.utils.Message.ASK_MENU_MESSAGE;

import christmas.model.Customer;
import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Sale;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Optional;

public class PromotionController {
    private Customer customer = null;

    public void run() {
        Optional<Date> date = read(this::getDate);
        Optional<Menu> menu = read(this::getMenu);

        if (date.isPresent() && menu.isPresent()) {
            Sale sale = new Sale(date.get(), menu.get());
            customer = new Customer(date.get(), menu.get(), sale.getMoney());
        }
        printOrderInfo(customer);
    }

    private Date getDate() {
        OutputView.println(ASK_DATE_MESSAGE);
        return new Date(InputView.getInput());
    }

    private Menu getMenu() {
        OutputView.println(ASK_MENU_MESSAGE);
        return new Menu(InputView.getInput());
    }


}
