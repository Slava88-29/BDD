package ru.netology.bdd.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.bdd.models.Card;
import ru.netology.bdd.models.User;
import ru.netology.bdd.pages.CardPage;
import ru.netology.bdd.pages.DashboardPage;
import ru.netology.bdd.pages.LoginPage;
import ru.netology.bdd.pages.VerifyPage;

import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CardsTest {
    // спецификация нужна для того, чтобы переиспользовать настройки в разных запросах

    public Card card1 = new Card("92df3f1c-a033-48e6-8390-206f6b1f56c0", "5559 0000 0000 0001");
    public Card card2 = new Card("0f3f5c2a-249e-4c3d-8287-09f7a039391d", "5559 0000 0000 0002");
    public User user = new User("vasya", "qwerty123", "12345", List.of(card1, card2));

    @BeforeEach
    void setup() {
        new LoginPage().loginIn(user);
        new VerifyPage().verifyIn(user);

    }

    @Test
    @DisplayName("Перевод с карты на карту суммы меньше чем есть на карте")
    void shouldTransactionSuccessAmountLessThanOnCard() {
        DashboardPage dashboardPage = new DashboardPage();
        Random random = new Random();
        int min = 0;
        int max = dashboardPage.getCardBalance(card2.getCardId())/5;
        int amount = random.nextInt(Math.abs(max));

        int card1Balance = dashboardPage.getCardBalance(card1.getCardId()) + amount;
        int card2Balance = dashboardPage.getCardBalance(card2.getCardId()) - amount;

        dashboardPage.clickDepositButton(card1.getCardId());
        CardPage cardPage = new CardPage();
        cardPage.setAmount(String.valueOf(amount)).setFrom(card2.getNumber()).clickButton();
        assertEquals(card1Balance, dashboardPage.getCardBalance(card1.getCardId()));
        assertEquals(card2Balance, dashboardPage.getCardBalance(card2.getCardId()));
    }

    @Test
    @DisplayName("Перевод с карты на карту суммы больще чем есть на карте")
    void shouldTransactionSuccessAmountGreaterThanOnCard() {
        DashboardPage dashboardPage = new DashboardPage();
        Random random = new Random();
        int min = dashboardPage.getCardBalance(card2.getCardId());
        int max = dashboardPage.getCardBalance(card2.getCardId())*2;
        int amount = random.nextInt(Math.abs(max-min+1))+min;

        int card1Balance = dashboardPage.getCardBalance(card1.getCardId());
        int card2Balance = dashboardPage.getCardBalance(card2.getCardId());

        dashboardPage.clickDepositButton(card1.getCardId());
        CardPage cardPage = new CardPage();
        cardPage.setAmount(String.valueOf(amount)).setFrom(card2.getNumber()).clickButton();
        assertEquals(card1Balance, dashboardPage.getCardBalance(card1.getCardId()));
        assertEquals(card2Balance, dashboardPage.getCardBalance(card2.getCardId()));
    }

    @Test
    @DisplayName("Перевод с карты на саму себя")
    void shouldTransactionSuccessSelf() {
        DashboardPage dashboardPage = new DashboardPage();
        Random random = new Random();
        int min = 0;
        int max = dashboardPage.getCardBalance(card2.getCardId());
        int amount = random.nextInt(Math.abs(max));

        int card1Balance = dashboardPage.getCardBalance(card1.getCardId());
        int card2Balance = dashboardPage.getCardBalance(card2.getCardId());

        dashboardPage.clickDepositButton(card1.getCardId());
        CardPage cardPage = new CardPage();
        cardPage.setAmount(String.valueOf(amount)).setFrom(card1.getNumber()).clickButton();
        assertEquals(card1Balance, dashboardPage.getCardBalance(card1.getCardId()));
        assertEquals(card2Balance, dashboardPage.getCardBalance(card2.getCardId()));
    }

    @Test
    @DisplayName("Перевод на карту суммы меньше одного рубля")
    void shouldTransactionSuccessAmountLessOneRuble() {
        DashboardPage dashboardPage = new DashboardPage();
        double amount0_1 = 0.1;

        double card1Balance = dashboardPage.getCardBalance(card1.getCardId()) + amount0_1;
        double card2Balance = dashboardPage.getCardBalance(card2.getCardId()) - amount0_1;

        dashboardPage.clickDepositButton(card1.getCardId());
        CardPage cardPage = new CardPage();
        cardPage.setAmount(String.valueOf(amount0_1)).setFrom(card2.getNumber()).clickButton();
        assertEquals(card1Balance, dashboardPage.getCardBalance(card1.getCardId()));
        assertEquals(card2Balance, dashboardPage.getCardBalance(card2.getCardId()));
    }

}