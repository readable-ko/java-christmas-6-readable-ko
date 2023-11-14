package christmas.model;

import static christmas.utils.MagicNumber.*;

public enum SaleType {
    NONE("없음") {
        @Override
        public int applySale(Date date, Menu menu) {
            return ZERO;
        }
    },
    CHRISTMAS_D_DAY_SALE("크리스마스 디데이 할인") {
        @Override
        public int applySale(Date date, Menu menu) {
            if (date.isBeforeDDay()) {
                int daySale = (date.getDay() - ONE) * DAY_SALE_AMOUNT;
                return THOUSAND + daySale;
            }
            return ZERO;
        }
    },
    WORKDAY_SALE("평일 할인") {
        @Override
        public int applySale(Date date, Menu menu) {
            if (date.getDateType().isWeekend()) {
                return ZERO;
            }
            return CourseMenu.DESSERT.countMenuFromCourse(menu.getOrderedMenu()) * DISCOUNT_AMOUNT;
        }
    },
    WEEKEND_SALE("주말 할인") {
        @Override
        public int applySale(Date date, Menu menu) {
            if (date.getDateType().isWorkday()) {
                return ZERO;
            }
            return CourseMenu.MAIN.countMenuFromCourse(menu.getOrderedMenu()) * DISCOUNT_AMOUNT;
        }
    },
    SPECIAL_SALE("특별 할인") {
        @Override
        public int applySale(Date date, Menu menu) {
            if (date.isHoliday()) {
                return THOUSAND;
            }
            return ZERO;
        }
    },
    GIVE_AWAY_EVENT("증정 이벤트") {
        @Override
        public int applySale(Date date, Menu menu) {
            int totalPrice = menu.getTotalPrice();
            if (totalPrice >= MIN_BENEFIT_AMOUNT) {
                return MenuType.CHAMPAGNE.getPrice(ONE);
            }
            return ZERO;
        }
    };

    private final String title;

    SaleType(String title) {
        this.title = title;
    }

    public abstract int applySale(Date date, Menu menu);

    @Override
    public String toString() {
        if (!title.equals(NONE.title)) {
            return title + ": ";
        }
        return title + "\n";
    }

    public String getTitle() {
        return title;
    }
}
