import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class MailRuSearchPage {
    MailRuSearchPage openPage() {
        open("https://go.mail.ru/");
        return this;
    }

    MailRuSearchPage search(String request) {
        $(By.name("q")).setValue(request).pressEnter();
        return this;
    }

    MailRuSearchPage resultEquals(String resultRef) {
        $(By.className("result")).shouldHave(Condition.text(resultRef));
        return this;
    }

    MailRuSearchPage checkSize(int size) {
        $$(By.className("result__li")).shouldHave(CollectionCondition.size(size));
        return this;
    }
}
