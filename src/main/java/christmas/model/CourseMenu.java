package christmas.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public enum CourseMenu {
    EMPTY("없음", Collections.emptyList()),
    APPETIZER("에피타이저", Arrays.asList(MenuType.MUSHROOM_SOUP, MenuType.TAPAS, MenuType.CAESAR_SALAD)),
    MAIN("메인", Arrays.asList(MenuType.T_BONE_STEAK, MenuType.BARBEQUE_RIP, MenuType.SEAFOOD_PASTA,
            MenuType.CHRISTMAS_PASTA)),
    DESSERT("디저트", Arrays.asList(MenuType.CHOCOLATE_CAKE, MenuType.ICE_CREAM)),
    DRINK("음료", Arrays.asList(MenuType.ZERO_COKE, MenuType.RED_WINE, MenuType.CHAMPAGNE));

    private final String title;
    private final List<MenuType> menuItems;

    CourseMenu(String title, List<MenuType> menuItems) {
        this.title = title;
        this.menuItems = menuItems;
    }

    public static CourseMenu findMenuFromCourse(MenuType menu) {
        return Arrays.stream(CourseMenu.values())
                .filter(courseMenu -> courseMenu.hasMenu(menu))
                .findAny()
                .orElse(EMPTY);
    }

    public int countMenuFromCourse(Map<MenuType, Integer> menuTypes) {
        return menuTypes.entrySet()
                .stream()
                .filter(entry -> menuItems.contains(entry.getKey()))
                .mapToInt(Entry::getValue).sum();
    }

    public boolean isDrink() {
        return this.title.equals(DRINK.title);
    }

    public boolean hasMenu(MenuType menu) {
        return menuItems.stream()
                .anyMatch(courseMenu -> courseMenu == menu);
    }

}
