package ru.netology.bdd.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CardPage {
    private final SelenideElement amountIn = $("[data-test-id=amount] input");
    private final SelenideElement fromIn = $("[data-test-id=from] input");
    private final SelenideElement button = $("[data-test-id=action-transfer]");

    public CardPage setAmount(String amount){
        amountIn.should(Condition.visible).setValue(amount);
        return this;
    }
    public CardPage setFrom(String from){
        fromIn.should(Condition.visible).setValue(from);
        return this;
    }
    public CardPage clickButton(){
        button.should(Condition.visible).click();
        return this;
    }
}
