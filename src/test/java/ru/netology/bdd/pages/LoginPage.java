package ru.netology.bdd.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private final SelenideElement form = $(".form");
    private final SelenideElement login = form.$("[name=login]");
    private final SelenideElement password = form.$("[name=password]");
    private final SelenideElement button = $(".button");

    public LoginPage() {
        open("http://localhost:9999");
    }
    public void loginIn () {
        login.setValue("vasya");
        password.setValue("qwerty123");
        button.click();
    }

    ;

}

