package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("Sale Class")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SaleTest {
    @Test
    void 만원_이하_세일_없음_테스트() {
        assertThat(new Sale(new Date("1"), new Menu("양송이수프-1")).getMoney().getTotalDiscountPrice()).isEqualTo(0);
    }

    @Test
    void 생성자_테스트() {
        assertThat(new Sale(new Date("3"), new Menu("제로콜라-3,타파스-1"))).isInstanceOf(Sale.class);
    }
}
