package ru.netology.bdd.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.bdd.models.User;

import static com.codeborne.selenide.Selenide.$;

public class VerifyPage {
    private final SelenideElement verify = $("[data-test-id=code] input");
    private final SelenideElement button = $(".button");

    public void verifyIn(User user) {

        verify.should(Condition.visible).setValue(user.getVerificationCode());
        button.should(Condition.visible).click();
    }

}

