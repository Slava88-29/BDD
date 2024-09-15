package ru.netology.bdd.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerifyPage {
    private final SelenideElement verify = $("[data-test-id=code] input");
    private final SelenideElement button = $(".button");

    public void verifyIn() {

        verify.should(Condition.visible).setValue("12345");
        button.should(Condition.visible).click();
    }

}

