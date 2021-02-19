package allure.test;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaStepsTest {
    private final static String REPOSITORY = "EpicFate/RepoForTest";
    private final static String USER = "EpicFate";
    private final static String PASSWORD = "";
    private final static String IssueName = "Problem 8";
    private final static String IssueNameText = "new unknown problem";
    private final static String url = "https://github.com";

    @Test
    public void CreateForIssue() {
        Allure.feature("Issues");
        Allure.story("должны увидеть Вопрос");
        Allure.link("https://github.com",url);
        Allure.parameter("Repository", REPOSITORY);

        step("Открывем главную страницу", (step) -> {
            open (url);
            step.parameter("name",url);
        });
        step("Авторизация", () -> {
            $("[href='/login']").click();
            $("#login_field").val(USER);
            $("#password").val(PASSWORD).pressEnter();
        });
        step("Ищем репозиторий", (step) -> {
            step.parameter("name",REPOSITORY);
            $(".header-search-input").val(REPOSITORY).pressEnter();
        });
        step("Переходим в репозиторий", () -> {
            $("#js-pjax-container").$(byLinkText(REPOSITORY)).click();
        });
        step("Переходим в раздел Issues", () -> {
            $(".js-sidenav-container-pjax").$(byText("Issues")).click();
        });
        step("Заполняем поля и подтверждаем", () -> {
            $(byLinkText("New issue")).click();
            $("#issue_title").val(IssueName);
            $("#issue_body").val(IssueNameText);
            $(byText("Submit new issue")).click();
        });
        step("Проверяем наличие Issue", () ->{
            $("#show_issue").shouldHave(text(IssueNameText));
        });
    }
}

