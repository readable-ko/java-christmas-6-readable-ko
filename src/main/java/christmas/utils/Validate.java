package christmas.utils;

import static christmas.utils.ErrorMessage.DRINK_ONLY_NOT_ALLOWED;
import static christmas.utils.ErrorMessage.IS_MORE_THAN_MAXIMUM;
import static christmas.utils.ErrorMessage.IS_NOT_DEFINED_MENU;
import static christmas.utils.ErrorMessage.IS_NOT_INTEGER_DATE;
import static christmas.utils.MagicNumber.MAX_CALENDAR_DAY;
import static christmas.utils.MagicNumber.ZERO;
import static christmas.utils.MagicNumber.ONE;
import static christmas.utils.MagicNumber.TWO;
import static christmas.utils.MagicNumber.MAX_MENU_VALUE;

import christmas.model.CourseMenu;
import christmas.model.MenuType;
import java.util.List;
import java.util.Map;

public class Validate {
    private static int count = ZERO;

    public static void isOnlyDrinks(Map<MenuType, Integer> orderedMenu) {
        if (orderedMenu.keySet().stream().allMatch(menu -> CourseMenu.findMenuFromCourse(menu).isDrink())) {
            throw new IllegalArgumentException(DRINK_ONLY_NOT_ALLOWED);
        }
    }

    public static void isInDateRange(int target) {
        isMoreThanMaximum(target, MAX_CALENDAR_DAY, IS_NOT_INTEGER_DATE);
        isLessThanMinimum(target, ONE, IS_NOT_INTEGER_DATE);
    }

    public static void isInMinMax(Map<MenuType, Integer> orderedMenu) {
        count = ZERO;

        for (MenuType menu : orderedMenu.keySet()) {
            int menuCount = orderedMenu.get(menu);
            isLessThanMinimum(menuCount, ONE, IS_NOT_DEFINED_MENU);
            count += menuCount;
        }

        isMoreThanMaximum(count, MAX_MENU_VALUE, IS_MORE_THAN_MAXIMUM);
    }

    private static void isMoreThanMaximum(int target, int range, String throwMessage) {
        if (target > range) {
            throw new IllegalArgumentException(throwMessage);
        }
    }

    private static void isLessThanMinimum(int target, int range, String throwMessage) {
        if (target < range) {
            throw new IllegalArgumentException(throwMessage);
        }
    }

    public static void isDifferentFormat(List<String> orderedMenu) {
        if (orderedMenu.size() != TWO) {
            throw new IllegalArgumentException(IS_NOT_DEFINED_MENU);
        }
    }

    public static void isDuplicateMenu() {
        throw new IllegalArgumentException(IS_NOT_DEFINED_MENU);
    }

    public static void isNotInMenu() {
        throw new IllegalArgumentException(IS_NOT_DEFINED_MENU);
    }
}
