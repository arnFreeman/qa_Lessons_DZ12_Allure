import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class Steps {
       @Step("Открываем главную страницу")
        public void openMainPage() {
            open("https://github.com");
        }
        @Step("Ищем репозиторий {repo}")
        public void searchForRepository(String repo) {
            $("[placeholder='Search or jump to...']").click();
            $("#query-builder-test").setValue(repo).pressEnter();
        }
        @Step("Открываем репозиторий {repo}")
        public void clickOnRepositoryLink(String repo) {
            $(linkText(repo)).click();
        }
        @Step("Открываем таб Issues")
        public void openIssuesTab() {
            $("#issues-tab").click();
        }
        @Step("Проверяем наличие Issue с номером {issue}")
        public void shouldSeeIssueWithName(String IssueName) {
            $("div[aria-label='Issues']").shouldHave(text(IssueName));
        }
}
