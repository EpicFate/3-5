package allure.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {
    private final static String REPOSITORY = "EpicFate/RepoForTest";
    private final static String USER = "EpicFate";
    private final static String PASSWORD = "";
    private final static String IssueName = "Problem 8";
    private final static String IssueNameText = "new unknown problem";
    private final static String url = "https://github.com";

    @Test
    public void CreateForIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(url);
        $("[href='/login']").click();
        $("#login_field").val(USER);
        $("#password").val(PASSWORD).pressEnter();
        $(".header-search-input").val(REPOSITORY).pressEnter();
        $("#js-pjax-container").$(byLinkText(REPOSITORY)).click();
        $(".js-sidenav-container-pjax").$(byText("Issues")).click();
        $(byLinkText("New issue")).click();
        $("#issue_title").val(IssueName);
        $("#issue_body").val(IssueNameText);
        $(byText("Submit new issue")).click();
        $("#show_issue").shouldHave(text(IssueNameText));
    }
}
