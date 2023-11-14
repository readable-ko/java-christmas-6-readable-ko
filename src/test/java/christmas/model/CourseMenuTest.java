package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("CourseMenu")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CourseMenuTest {
    @ParameterizedTest
    @MethodSource("provideMEnuTypeWithCourseMenuType")
    void 메뉴가_포함되어_있다면_해당_코스_메뉴_반환(MenuType menuType, CourseMenu courseMenu) {
        assertThat(CourseMenu.findMenuFromCourse(menuType)).isEqualTo(courseMenu);
    }

    private static Stream<Arguments> provideMEnuTypeWithCourseMenuType() {
        return Stream.of(
                Arguments.of(MenuType.T_BONE_STEAK, CourseMenu.MAIN),
                Arguments.of(MenuType.MUSHROOM_SOUP, CourseMenu.APPETIZER),
                Arguments.of(MenuType.ZERO_COKE, CourseMenu.DRINK),
                Arguments.of(MenuType.ICE_CREAM, CourseMenu.DESSERT)
        );
    }

    @Test
    void 메뉴가_포함되지_않으면_EMPTY를_반환() {
        assertThat(CourseMenu.findMenuFromCourse(MenuType.NONE)).isEqualTo(CourseMenu.EMPTY);
    }

    @ParameterizedTest
    @MethodSource("provideEnumMapWithCourseMenu")
    void 해당_코스_메뉴에_속한_메뉴_갯수_반환(EnumMap<MenuType, Integer> enumMap, CourseMenu courseMenu, int answer) {
        assertThat(courseMenu.countMenuFromCourse(enumMap)).isEqualTo(answer);
    }

    private static Stream<Arguments> provideEnumMapWithCourseMenu() {
        EnumMap<MenuType, Integer> enumMap1 = new EnumMap<>(MenuType.class);
        enumMap1.put(MenuType.BARBEQUE_RIP, 5);
        enumMap1.put(MenuType.SEAFOOD_PASTA, 5);
        enumMap1.put(MenuType.CHAMPAGNE, 2);
        enumMap1.put(MenuType.CHRISTMAS_PASTA, 1);

        EnumMap<MenuType, Integer> enumMap2 = new EnumMap<>(MenuType.class);
        enumMap2.put(MenuType.NONE, 0);

        return Stream.of(
                Arguments.of(enumMap1, CourseMenu.MAIN, 11),
                Arguments.of(enumMap1, CourseMenu.DRINK, 2),
                Arguments.of(enumMap1, CourseMenu.EMPTY, 0),
                Arguments.of(enumMap2, CourseMenu.DRINK, 0),
                Arguments.of(enumMap2, CourseMenu.EMPTY, 0)
        );
    }

    @Test
    void 음료인지_확인() {
        assertThat(Arrays.stream(CourseMenu.values())
                .allMatch(courseMenu -> {
                    if (courseMenu == CourseMenu.DRINK) {
                        return !courseMenu.isDrink();
                    }
                    return courseMenu.isDrink();
                }))
                .isFalse();

        assertThat(CourseMenu.DRINK.isDrink()).isTrue();
    }
}
