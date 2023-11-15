package christmas.model;

import static christmas.utils.ErrorMessage.DRINK_ONLY_NOT_ALLOWED;
import static christmas.utils.ErrorMessage.IS_MORE_THAN_MAXIMUM;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Menu Class")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MenuTest {
    private final static String message = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1, 아이스크림-3", "아이스크림-1", "초코케이크-3 , 양 송 이 수프 - 5,    샴페인- 01"})
    void Menu_생성자_정상_출력_확인(String argument) {
        assertThat(new Menu(argument)).isInstanceOf(Menu.class);
        assertThatCode(() -> new Menu(argument)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"잠온다-3", "아이스크림-3, 실례가-1,안된다면-3"})
    void 없는_메뉴_입력시_예외_처리(String argument) {
        assertThatThrownBy(() -> new Menu(argument)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @ParameterizedTest
    @ValueSource(strings = {"아이스크림-3.141592, 레드와인-3", "크리스마스파스타-0", "해물파스타-ONE",
            "바비큐립-공백", "양송이수프-!@#"})
    void 메뉴_개수_1개_이하_숫자_예외_처리(String argument) {
        assertThatThrownBy(() -> new Menu(argument)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @ParameterizedTest
    @ValueSource(strings = {"아이스크림-1,,레드와인-3", "아이스크림,레드와인,해물파스타", "해물파스타-1 양송이수프-3", "해물파스타--1,티본스테이크-3"})
    void 메뉴_형식이_다른_경우_예외_처리(String argument) {
        assertThatThrownBy(() -> new Menu(argument)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @ParameterizedTest
    @ValueSource(strings = {"아이스크림-3,아이스크림-2,아이스크림-1", "레드와인-3,아이스크림-1,레드와인-1", "샴페인-3,샴페인-3"})
    void 중복_메뉴를_입력한_경우_예외_처리(String argument) {
        assertThatThrownBy(() -> new Menu(argument)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-42", "해산물파스타-987654321", "해산물파스타-10,제로콜라-10,레드와인-1"})
    void 메뉴_20개_이상_주문_불가_예외_처리(String argument) {
        assertThatThrownBy(() -> new Menu(argument)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-4294967297", "제로콜라-2147483648", "제로콜라-2147483649"})
    void 정수값_이상_주문_예외_처리(String argument) {
        assertThatThrownBy(() -> new Menu(argument)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-2, 레드와인-3, 샴페인-1", "제로콜라-15", "레드와인-3", "샴페인-5"})
    void 음료만_시킬_경우_주문_불가_예외_처리(String argument) {
        assertThatThrownBy(() -> new Menu(argument)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 음료만 주문 시, 주문할 수 없습니다.");
    }
}
