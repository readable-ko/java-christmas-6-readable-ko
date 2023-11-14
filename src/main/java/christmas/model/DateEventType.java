package christmas.model;

import static christmas.utils.MagicNumber.LAST_CHRISTMAS_SALE_DAY;
import static christmas.utils.MagicNumber.ONE;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public enum DateEventType {
    NONE("NONE", false),
    MONDAY("MONDAY", true),
    TUESDAY("TUESDAY", true),
    WEDNESDAY("WEDNESDAY", true),
    THURSDAY("THURSDAY", true),
    FRIDAY("FRIDAY", false),
    SATURDAY("SATURDAY", false),
    SUNDAY("SUNDAY", true);

    private final String day;

    private final boolean isWorkday;

    DateEventType(String day, boolean isWorkday) {
        this.day = day;
        this.isWorkday = isWorkday;
    }

    public static DateEventType findDateOfWeek(int day) {
        Calendar calendar = Calendar.getInstance();
        String dayOfWeek = LocalDate.of(calendar.get(Calendar.YEAR), Calendar.DECEMBER + ONE, day)
                .getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.US)
                .toUpperCase();

        return Arrays.stream(DateEventType.values())
                .filter(dateEventType -> dateEventType.day.equals(dayOfWeek))
                .findAny()
                .orElse(NONE);
    }

    public boolean isWorkday() {
        return isWorkday;
    }

    public boolean isWeekend() {
        return !isWorkday;
    }
}
