import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import domain.UnderlineMenuItems;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ParametriseTests {

    @CsvSource(value = {
            "selenide/selenide",
            "pytest"
    })

    @ParameterizedTest(name = "{0}")
    void githubSearchTest(String searchText) {
        open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(searchText);
        $(".header-search-input").submit();
        $(".repo-list").shouldHave(text(searchText));
    }

    @EnumSource(UnderlineMenuItems.class)
    @ParameterizedTest(name = "{1}")
    void githubUnderlineNavigationTest (UnderlineMenuItems underlineMenuItems) {
        open("https://github.com/selenide/selenide");
        $(underlineMenuItems.getDesc()).click();
    }

    static Stream<Arguments> githubLoginMethod() {
        return Stream.of(
                Arguments.of(
                        "sgdsdg", "111"
                ),
                Arguments.of(
                        "ya.ru", "222"
                )
        );
    }

    @MethodSource("githubLoginMethod")
    @ParameterizedTest(name = "{2}")
    void githubLoginNegativeTest(String login, String password) {
        open("https://github.com/login");
        $("#login_field").sendKeys(login);
        $("#password").sendKeys(password);
        $(".btn-primary").click();
        $(".flash-error").shouldBe(visible);
    }
}
