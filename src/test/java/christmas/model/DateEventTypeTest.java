package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("DateEventType")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DateEventTypeTest {
    @ParameterizedTest
    @MethodSource("DayAndDateEventType")
    void 올해_2023년_12월_해당_날짜의_요일을_반환(int argument, DateEventType dayOfWeek) {
        assertThat(DateEventType.findDateOfWeek(argument)).isEqualTo(dayOfWeek);
    }

    private static Stream<Arguments> DayAndDateEventType() {
        return Stream.of(
                Arguments.of(1, DateEventType.FRIDAY),
                Arguments.of(2, DateEventType.SATURDAY),
                Arguments.of(26, DateEventType.TUESDAY),
                Arguments.of(30, DateEventType.SATURDAY),
                Arguments.of(31, DateEventType.SUNDAY),
                Arguments.of(13, DateEventType.WEDNESDAY),
                Arguments.of(12, DateEventType.TUESDAY),
                Arguments.of(18, DateEventType.MONDAY),
                Arguments.of(21, DateEventType.THURSDAY)
        );
    }

    @Test
    void 금요일_토요일이면_주말이고_이외에는_평일임을_반환() {
        assertThat(DateEventType.SATURDAY.isWeekend() && DateEventType.FRIDAY.isWorkday()).isFalse();

        List<DateEventType> valid = Arrays.asList(DateEventType.MONDAY, DateEventType.TUESDAY, DateEventType.WEDNESDAY,
                DateEventType.SUNDAY, DateEventType.THURSDAY);
        assertThat(valid.stream().allMatch(DateEventType::isWorkday)).isTrue();
        assertThat(valid.stream().anyMatch(DateEventType::isWeekend)).isFalse();
    }
}
