package ru.netology.bdd.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.bdd.pages.CardPage;
import ru.netology.bdd.pages.DashboardPage;
import ru.netology.bdd.pages.LoginPage;
import ru.netology.bdd.pages.VerifyPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
        DashboardPage dashboardPage = new DashboardPage();
        int amount2000 = 2000;
        String numberCard1 = "5559 0000 0000 0001";
        String numberCard2 = "5559 0000 0000 0002";

        String idCard1 = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        String idCard2 = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        int card1Balance = dashboardPage.getCardBalance(idCard1) + amount2000;
        int card2Balance = dashboardPage.getCardBalance(idCard2) - amount2000;

        dashboardPage.clickDepositButton(idCard1);
        CardPage cardPage = new CardPage();
        cardPage.setAmount(String.valueOf(amount2000)).setFrom(numberCard2).clickButton();
        assertEquals(card1Balance, dashboardPage.getCardBalance(idCard1));
        assertEquals(card2Balance, dashboardPage.getCardBalance(idCard2));
    }

    @Test
    @DisplayName("Перевод с карты на карту суммы больще чем есть на карте")
    void shouldTransactionSuccessAmountGreaterThanOnCard() {
        DashboardPage dashboardPage = new DashboardPage();
        int amount12000 = 12000;
        String numberCard1 = "5559 0000 0000 0001";
        String numberCard2 = "5559 0000 0000 0002";

        String idCard1 = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        String idCard2 = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        int card1Balance = dashboardPage.getCardBalance(idCard1);
        int card2Balance = dashboardPage.getCardBalance(idCard2);

        dashboardPage.clickDepositButton(idCard1);
        CardPage cardPage = new CardPage();
        cardPage.setAmount(String.valueOf(amount12000)).setFrom(numberCard2).clickButton();
        assertEquals(card1Balance, dashboardPage.getCardBalance(idCard1));
        assertEquals(card2Balance, dashboardPage.getCardBalance(idCard2));
    }

    @Test
    @DisplayName("Перевод с карты на саму себя")
    void shouldTransactionSuccessSelf() {
        DashboardPage dashboardPage = new DashboardPage();
        int amount2000 = 2000;
        String numberCard1 = "5559 0000 0000 0001";
        String numberCard2 = "5559 0000 0000 0002";

        String idCard1 = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        String idCard2 = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        int card1Balance = dashboardPage.getCardBalance(idCard1);
        int card2Balance = dashboardPage.getCardBalance(idCard2);

        dashboardPage.clickDepositButton(idCard1);
        CardPage cardPage = new CardPage();
        cardPage.setAmount(String.valueOf(amount2000)).setFrom(numberCard1).clickButton();
        assertEquals(card1Balance, dashboardPage.getCardBalance(idCard1));
        assertEquals(card2Balance, dashboardPage.getCardBalance(idCard2));
    }

    @Test
    @DisplayName("Перевод на карту суммы меньше одного рубля")
    void shouldTransactionSuccessAmountLessOneRuble() {
        DashboardPage dashboardPage = new DashboardPage();
        double amount0_1 = 0.1;
        String numberCard1 = "5559 0000 0000 0001";
        String numberCard2 = "5559 0000 0000 0002";

        String idCard1 = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        String idCard2 = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        double card1Balance = dashboardPage.getCardBalance(idCard1) + amount0_1;
        double card2Balance = dashboardPage.getCardBalance(idCard2) - amount0_1;

        dashboardPage.clickDepositButton(idCard1);
        CardPage cardPage = new CardPage();
        cardPage.setAmount(String.valueOf(amount0_1)).setFrom(numberCard2).clickButton();
        assertEquals(card1Balance, dashboardPage.getCardBalance(idCard1));
        assertEquals(card2Balance, dashboardPage.getCardBalance(idCard2));
    }

}