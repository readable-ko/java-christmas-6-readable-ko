package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("DateType")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DateTest {
    @ParameterizedTest
    @ValueSource(strings = {"3.14", "hello", "45", "-1"})
    void 생성자_예외값_입력시_예외_처리(String argument) {
        assertThatThrownBy(() -> new Date(argument)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"25", "3"})
    void 특별할인_날짜_확인_테스트(String argument) {
        assertThat(new Date(argument).isHoliday()).isTrue();
        assertThat(new Date(argument).isBeforeDDay()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"26", "27", "28", "29", "30"})
    void 특별할인_날짜_아닌_경우_테스트(String argument) {
        assertThat(new Date(argument).isHoliday()).isFalse();
        assertThat(new Date(argument).isBeforeDDay()).isFalse();
    }
}
