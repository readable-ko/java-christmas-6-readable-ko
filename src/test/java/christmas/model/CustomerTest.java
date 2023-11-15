package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("Customer Class")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CustomerTest {
    @Test
    void 생성자_테스트() {
        EnumMap<SaleType, Integer> enumMap = new EnumMap<>(SaleType.class);
        enumMap.put(SaleType.NONE, 0);
        assertThat(new Customer(new Date("5"), new Menu("해산물파스타-2,제로콜라-1"), new Money(20000, enumMap))).isInstanceOf(
                Customer.class);
    }
}
