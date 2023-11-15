package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Badge Class")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BadgeTest {
    @ParameterizedTest
    @ValueSource(ints = {4999, 3000, 0})
    void 배지_없음_테스트(int amount) {
        assertThat(Badge.getBadge(amount)).isEqualTo(Badge.NONE);
    }

    @ParameterizedTest
    @ValueSource(ints = {5000, 9000, 9999})
    void 별_배지_테스트(int amount) {
        assertThat(Badge.getBadge(amount)).isEqualTo(Badge.STAR);
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 15000, 19999, 19000})
    void 트리_배지_테스트(int amount) {
        assertThat(Badge.getBadge(amount)).isEqualTo(Badge.TREE);
    }

    @ParameterizedTest
    @ValueSource(ints = {20000, 300000})
    void 산타_배지_테스트(int amount) {
        assertThat(Badge.getBadge(amount)).isEqualTo(Badge.SANTA);
    }
}
