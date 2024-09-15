package ru.netology.bdd.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private final SelenideElement form = $(".form");
    private final SelenideElement login = form.$("[name=login]");
    private final SelenideElement password = form.$("[name=password]");
    private final SelenideElement button = $(".button");

    public LoginPage() {
        System.setProperty("chromeoptions.prefs", "profile.password_manager_leak_detection=false");
       open("http://localhost:9999");
    }

    public void loginIn() {
        login.should(Condition.visible).setValue("vasya");
        password.should(Condition.visible).setValue("qwerty123");
        button.should(Condition.visible).click();
    }


}

