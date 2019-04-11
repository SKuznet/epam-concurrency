package com.epam.concurrency.homework1;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class ATM {

    synchronized void getMoney(User user, BigDecimal amount) {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println(user.getName() + " try to get " + amount + "...");
        System.err.println("Balance: " + user.getAmount());
        if (user.getAmount().compareTo(amount) < 0) {
            System.err.println(user.getName() + " don't have enough money" + "\n");
        } else {
            BigDecimal newAmount = user.getAmount().subtract(amount);
            user.setAmount(newAmount);
            System.err.println(user.getName() + " all ok! New amount: " + newAmount + "\n");
        }
    }
}
