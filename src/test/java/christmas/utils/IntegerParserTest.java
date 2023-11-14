package christmas.utils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("IntegerParser Class")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class IntegerParserTest {
    @ParameterizedTest
    @ValueSource(strings = {"3910100000", "asl", "3.1415"})
    void 숫자_파싱_예외_처리_테스트(String argument) {
        assertThatThrownBy(() -> IntegerParser.convert(argument, "ERROR"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자_정상_파싱_테스트() {
        assertThat(IntegerParser.convert("035", "ERROR")).isEqualTo(35);

    }
}
