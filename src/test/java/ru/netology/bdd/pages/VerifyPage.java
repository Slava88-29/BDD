package ru.netology.bdd.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerifyPage {
    private final SelenideElement verify = $("[name=code]");
    private final SelenideElement button = $(".button");

    public void verifyIn() {

        verify.setValue("12345");
        button.click();
    }

}

