package christmas.model;

import static christmas.utils.ErrorMessage.IS_NOT_DEFINED_MENU;
import static christmas.utils.MagicNumber.ONE;
import static christmas.utils.MagicNumber.ZERO;

import christmas.utils.IntegerParser;
import christmas.utils.Validate;
import java.util.EnumMap;
import java.util.List;

public class Menu {
    private final EnumMap<MenuType, Integer> orderedMenu = new EnumMap<>(MenuType.class);

    public Menu(String ordered) {
        final List<String> orderedMenuWithCounts = List.of(ordered.replace(" ", "").split(","));
        split(orderedMenuWithCounts);
        validation();
    }

    private void split(List<String> orderedMenuWithCounts) {
        for (String order : orderedMenuWithCounts) {
            List<String> part = List.of(order.split("-"));
            Validate.isDifferentFormat(part);

            MenuType menu = findMenu(part);

            int count = IntegerParser.convert(part.get(ONE), IS_NOT_DEFINED_MENU);
            addMenu(menu, count);
        }
    }

    private MenuType findMenu(List<String> part) {
        MenuType menu = MenuType.findMenuByTitle(part.get(ZERO));

        if (menu.isNone()) {
            Validate.isNotInMenu();
        }
        return menu;
    }

    private void addMenu(MenuType menu, int count) {
        if (orderedMenu.containsKey(menu)) {
            Validate.isDuplicateMenu();
        }

        orderedMenu.put(menu, count);
    }

    private void validation() {
        Validate.isInMinMax(orderedMenu);
        Validate.isOnlyDrinks(orderedMenu);
    }

    public int getTotalPrice() {
        return orderedMenu.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrice(entry.getValue()))
                .sum();
    }

    public EnumMap<MenuType, Integer> getOrderedMenu() {
        return orderedMenu;
    }
}
