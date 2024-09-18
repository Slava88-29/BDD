package ru.netology.bdd.utilus;

import java.util.Random;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
    @Value
    public static class Card {
        private String cardId;
        private String number;
    }

    public static Card getCard1() {
        return new Card("92df3f1c-a033-48e6-8390-206f6b1f56c0", "5559 0000 0000 0001");
    }

    public static Card getCard2() {
        return new Card("0f3f5c2a-249e-4c3d-8287-09f7a039391d", "5559 0000 0000 0002");

    }

    public static int getAmount(int balance, int minK, int maxK) {
        Random random = new Random();
        int min = Math.abs(balance * minK);
        int max = Math.abs(balance * maxK);
        int amount = random.nextInt(Math.abs(max - min + 1)) + max;
        return amount;
    }
}
