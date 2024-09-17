package ru.netology.bdd.utilus;

import ru.netology.bdd.models.Card;
import ru.netology.bdd.models.User;

import java.util.List;
import java.util.Random;

public class DataHelper {
    public static User getUser() {
        Card card1 = new Card("92df3f1c-a033-48e6-8390-206f6b1f56c0", "5559 0000 0000 0001");
        Card card2 = new Card("0f3f5c2a-249e-4c3d-8287-09f7a039391d", "5559 0000 0000 0002");
        User user = new User("vasya", "qwerty123", "12345", List.of(card1, card2));
        return user;
    }
    public static int getAmount(int balance, int minK, int maxK){
        Random random = new Random();
        int min = Math.abs(balance*minK);
        int max = Math.abs(balance*maxK);
        int amount = random.nextInt(Math.abs(max-min+1))+max;
        return amount;
    }
}
