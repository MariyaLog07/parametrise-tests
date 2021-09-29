import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.textCaseSensitive;
import static com.codeborne.selenide.Selenide.*;

import domain.UnderlineMenuItems;

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

}
