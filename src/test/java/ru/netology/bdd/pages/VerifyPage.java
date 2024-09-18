package ru.netology.bdd.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.bdd.utilus.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerifyPage {
    private final SelenideElement verify = $("[data-test-id=code] input");
    private final SelenideElement button = $(".button");

    public void verifyIn(DataHelper.VerificationCode code) {

        verify.should(Condition.visible).setValue(code.getCode());
        button.should(Condition.visible).click();
    }

}

