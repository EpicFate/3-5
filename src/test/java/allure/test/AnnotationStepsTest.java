package allure.test;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import io.qameta.allure.Step;


public class AnnotationStepsTest {

    private final static String REPOSITORY = "EpicFate/RepoForTest";
    private final static String USER = "EpicFate";
    private final static String PASSWORD = "";
    private final static String IssueName = "Problem 8";
    private final static String IssueNameText = "new unknown problem";

    @Test
    @DisplayName("Тест с аннотациями")
    @Feature("Issues")
    @Story("должны увидеть Вопрос")
    @Owner("EpicFate")
    @Severity(SeverityLevel.NORMAL)
    public void CreateForIssue() {
        final BaseSteps steps = new BaseSteps();

        steps.openMainPage();
        steps.authorization();
        steps.searchForRepository();
        steps.goToRepository();
        steps.goToIssues();
        steps.createToIssue();
        steps.shouldSeeIssue();
    }

    public static class BaseSteps {
        @Step("Открывем главную страницу")
        public void openMainPage() {
            open ("https://github.com"); }

        @Step("Авторизация")
        public void authorization() {
            $("[href='/login']").click();
            $("#login_field").val(USER);
            $("#password").val(PASSWORD).pressEnter(); }

        @Step("Ищем репозиторий")
        public void searchForRepository() {
            $(".header-search-input").val(REPOSITORY).pressEnter(); }

        @Step("Переходим в репозиторий ")
        public void goToRepository() {
            $("#js-pjax-container").$(byLinkText(REPOSITORY)).click();}

        @Step("Переходим в раздел Issues")
        public void goToIssues() {
            $(".js-sidenav-container-pjax").$(byText("Issues")).click();}

        @Step("Заполняем поля и подтверждаем")
        public void createToIssue() {
            $(byLinkText("New issue")).click();
            $("#issue_title").val(IssueName);
            $("#issue_body").val(IssueNameText);
            $(byText("Submit new issue")).click();}

        @Step("Проверяем наличие Issue")
        public void shouldSeeIssue() {
             $("#show_issue").shouldHave(text(IssueNameText));}
    }
}