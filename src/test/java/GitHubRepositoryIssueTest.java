import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class GitHubRepositoryIssueTest {
    private static final String RepositoryLink = "arnFreeman/qa_Lessons_DZ8_PracticeFormPOWithTDG",
            IssueName = "Issue for DZ12";
    @Test
    @DisplayName("Clean Selenide (with Listener)")
    void checkIssueTabWithListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $("[placeholder='Search or jump to...']").click();
        $("#query-builder-test").setValue(RepositoryLink).pressEnter();
        $(linkText(RepositoryLink)).click();
        $("#issues-tab").click();
        $("div[aria-label='Issues']").shouldHave(text(IssueName));
    }
    @Test
    @DisplayName("Lambda steps via step")
    public void checkIssueTabWithLambdaStepsViaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> open("https://github.com"));
        step("Ищем репозиторий", () -> {
            $("[placeholder='Search or jump to...']").click();
            $("#query-builder-test").setValue(RepositoryLink).pressEnter();
        });
        step("Открываем репозиторий" + RepositoryLink, () -> $(linkText(RepositoryLink)).click());
        step("Открываем таб Issue", () -> $("#issues-tab").click());
        step("Проверяем наличие Issue с именем" + IssueName, () -> {
            $("div[aria-label='Issues']").shouldHave(text(IssueName));
        });
    }
    @Test
    @DisplayName("Steps with @Step annotation")
    public void checkIssueTabWithStepAnnotation() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Steps steps = new Steps();
        steps.openMainPage();
        steps.searchForRepository(RepositoryLink);
        steps.clickOnRepositoryLink(RepositoryLink);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithName(IssueName);
    }
}
