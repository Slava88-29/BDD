package ru.netology.bdd.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.bdd.pages.LoginPage;
import ru.netology.bdd.pages.VerifyPage;

import static com.codeborne.selenide.Selenide.*;


class CardsTest {
    // спецификация нужна для того, чтобы переиспользовать настройки в разных запросах


    @BeforeEach
    void setup() {
        new LoginPage().loginIn();
        new VerifyPage().verifyIn();

    }

    @Test
    @DisplayName("Перевод с карты на карту суммы меньше чем есть на карте")
    void shouldTransactionSuccessAmountLessThanOnCard() {

    }
    @Test
    @DisplayName("Перевод с карты на карту суммы больще чем есть на карте")
    void shouldTransactionSuccessAmountGreaterThanOnCard() {

    }
    @Test
    @DisplayName("Перевод с карты на саму себя")
    void shouldTransactionSuccessSelf() {

    }
    @Test
    @DisplayName("Перевод на карту суммы меньше одного рубля")
    void shouldTransactionSuccessAmountLessOneRuble() {
    }
}