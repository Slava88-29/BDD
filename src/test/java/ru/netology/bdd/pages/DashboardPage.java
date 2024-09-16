package ru.netology.bdd.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement main =  $x("//h2[contains(text(),'Личный кабинет')]");

    public DashboardPage() {
        main.shouldBe(visible);
//
    }

    public int getCardBalance(String id) {
        // TODO: перебрать все карты и найти по атрибуту data-test-id
        var card = cards.filter(Condition.attribute("data-test-id", id)).get(0);
        var text = card.should(Condition.visible).getText();
        return extractBalance(text);

    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void clickDepositButton(String id) {
        var card = cards.filter(Condition.attribute("data-test-id", id)).get(0);
        card.should(Condition.visible).$("[data-test-id=action-deposit]").click();
    }
}