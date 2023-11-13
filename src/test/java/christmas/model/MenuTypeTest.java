package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("MenuType")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MenuTypeTest {
    @ParameterizedTest
    @MethodSource("provideStringWithMatchingMenuType")
    void 문자열이_포함되어_있으면_해당_MenuType을_반환(String title, MenuType menuType) {
        assertThat(MenuType.findMenuByTitle(title)).isEqualTo(menuType);
    }

    private static Stream<Arguments> provideStringWithMatchingMenuType() {
        return Stream.of(
                Arguments.of("티본스테이크", MenuType.T_BONE_STEAK),
                Arguments.of("양송이수프", MenuType.MUSHROOM_SOUP),
                Arguments.of("시저샐러드", MenuType.CAESAR_SALAD),
                Arguments.of("아이스크림", MenuType.ICE_CREAM)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"백본스테이크", "소송이수프", "시저시저샐러드", "어른스크림"})
    void 문자열이_포함되지_않으면_None을_반환(String title) {
        assertThat(MenuType.findMenuByTitle(title)).isEqualTo(MenuType.NONE);
    }

    @Test
    void None이면_true를_반환_그_외에는_false_반환() {
        assertThat(MenuType.NONE.isNone()).isTrue();
        assertThat(MenuType.BARBEQUE_RIP.isNone()).isFalse();
    }
}
