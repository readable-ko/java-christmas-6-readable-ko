package christmas.model;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.EnumMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Money Class")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MoneyTest {
    @ParameterizedTest
    @MethodSource("enumMapProvider")
    void 생성_및_할인_총액_테스트(int totalOrderPrice, EnumMap<SaleType, Integer> enumMap, int discountPrice) {
        Money money = new Money(totalOrderPrice, enumMap);
        assertThat(money.getTotalOrderPrice()).isEqualTo(totalOrderPrice);
        assertThat(money.getTotalDiscountPrice()).isEqualTo(discountPrice);
        assertThat(money.getTotalChargePrice()).isEqualTo(totalOrderPrice - discountPrice);
    }

    private static Stream<Arguments> enumMapProvider() {

        EnumMap<SaleType, Integer> enumMap = new EnumMap<>(SaleType.class);
        EnumMap<SaleType, Integer> enumMap2 = new EnumMap<>(SaleType.class);
        enumMap.put(SaleType.WORKDAY_SALE, 0);
        enumMap2.put(SaleType.CHRISTMAS_D_DAY_SALE, 10000);
        enumMap2.put(SaleType.WORKDAY_SALE, 10000);
        return Stream.of(
                Arguments.of(10000, enumMap, 0),
                Arguments.of(30000, enumMap2, 20000)
        );
    }
}
