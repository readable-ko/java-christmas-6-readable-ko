package christmas.model;

import static christmas.utils.MagicNumber.LAST_CHRISTMAS_SALE_DAY;
import static christmas.utils.ErrorMessage.IS_NOT_INTEGER_DATE;

import christmas.utils.Parser;
import christmas.utils.Validate;

public class Date {
    private final DateEventType dateEventType;
    private final int day;

    public Date(String date) {
        day = Parser.intConvert(date, IS_NOT_INTEGER_DATE);
        validation();

        dateEventType = DateEventType.findDateOfWeek(day);
    }

    private void validation() {
        Validate.isInDateRange(day);
    }

    public boolean isBeforeDDay() {
        return day <= LAST_CHRISTMAS_SALE_DAY;
    }

    public boolean isHoliday() {
        return day == LAST_CHRISTMAS_SALE_DAY || dateEventType == DateEventType.SUNDAY;
    }

    public DateEventType getDateType() {
        return dateEventType;
    }

    public int getDay() {
        return day;
    }
}
