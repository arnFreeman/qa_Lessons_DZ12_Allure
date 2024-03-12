import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.SetValueOptions.withText;
import static org.openqa.selenium.By.linkText;

public class GitHubRepositoryIssueTest {

    @DisplayName("Тест на проверку названия Issue в репозитории через Web-интерфейс")
    @Test
    @DisplayName("Чистый Selenide (с Listener)")
    void checkIssueTabWithListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $("[placeholder='Search or jump to...']").click();
        $("#query-builder-test").setValue("arnFreeman/qa_Lessons_DZ8_PracticeFormPOWithTDG").pressEnter();
        $(linkText("arnFreeman/qa_Lessons_DZ8_PracticeFormPOWithTDG")).click();
        $("#issues-tab").click();
        $((WebElement) withText("#2")).should(Condition.exist);
    }

}
