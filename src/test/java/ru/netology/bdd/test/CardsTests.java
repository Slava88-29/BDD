package ru.netology.bdd.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.bdd.pages.CardPage;
import ru.netology.bdd.pages.DashboardPage;
import ru.netology.bdd.pages.LoginPage;
import ru.netology.bdd.pages.VerifyPage;
import ru.netology.bdd.utilus.DataHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CardsTest {
    // спецификация нужна для того, чтобы переиспользовать настройки в разных запросах

    public DataHelper.AuthInfo user=DataHelper.getAuthInfo();
    public DataHelper.VerificationCode code=DataHelper.getVerificationCodeFor(user);

    public DataHelper.Card card1=DataHelper.getCard1();
    public DataHelper.Card card2=DataHelper.getCard2();
    @BeforeEach
    void setup() {
        new LoginPage().loginIn(user);
        new VerifyPage().verifyIn(code);

    }

    @Test
    @DisplayName("Перевод с карты на карту суммы меньше чем есть на карте")
    void shouldTransactionSuccessAmountLessThanOnCard() {
        DashboardPage dashboardPage = new DashboardPage();

        int balance = dashboardPage.getCardBalance(card2.getCardId());
        int amount = DataHelper.getAmount(balance,0,1);

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

        int balance = dashboardPage.getCardBalance(card2.getCardId());
        int amount = DataHelper.getAmount(balance,1,2);


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
        int balance = dashboardPage.getCardBalance(card2.getCardId());
        int amount = DataHelper.getAmount(balance,0,1);


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