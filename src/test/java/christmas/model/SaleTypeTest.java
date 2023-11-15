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

@DisplayName("SaleType Enum")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SaleTypeTest {
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

    @Test
    void 크리스마스_할인_테스트() {
        assertThat(SaleType.CHRISTMAS_D_DAY_SALE.applySale(new Date("1"), new Menu("바비큐립-2"))).isEqualTo(1000);
        assertThat(SaleType.CHRISTMAS_D_DAY_SALE.applySale(new Date("26"), new Menu("바비큐립-2"))).isEqualTo(0);
        assertThat(SaleType.CHRISTMAS_D_DAY_SALE.applySale(new Date("2"), new Menu("아이스크림-1"))).isEqualTo(1100);
    }

    @Test
    void 평일_할인_테스트() {
        assertThat(SaleType.WORKDAY_SALE.applySale(new Date("14"),
                new Menu("양송이수프-1,티본스테이크-1,제로콜라-1,바비큐립-1,크리스마스파스타-3"))).isEqualTo(0);
        assertThat(SaleType.WORKDAY_SALE.applySale(new Date("3"), new Menu("아이스크림-1,타파스-1,제로콜라-1"))).isEqualTo(2023);
    }

    @Test
    void 주말_할인_테스트() {
        assertThat(SaleType.WEEKEND_SALE.applySale(new Date("15"), new Menu("바비큐립-2"))).isEqualTo(4046);
        assertThat(SaleType.WEEKEND_SALE.applySale(new Date("2"), new Menu("양송이수프-1,제로콜라-1,초코케이크-2"))).isEqualTo(0);
    }

    @Test
    void 특별_할인_테스트() {
        assertThat(SaleType.SPECIAL_SALE.applySale(new Date("25"), new Menu("티본스테이크-1,"))).isEqualTo(1000);
        assertThat(SaleType.SPECIAL_SALE.applySale(new Date("24"), new Menu("초코케이크-1"))).isEqualTo(1000);
        assertThat(SaleType.SPECIAL_SALE.applySale(new Date("27"), new Menu("티본스테이크-1"))).isEqualTo(0);
    }
}
