package com.epam.concurrency.hw1;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Account account = new Account(1, BigDecimal.valueOf(200));
        ATM atm1 = new ATM(1);
        atm1.setBalance(BigDecimal.valueOf(5000));
        new Thread(() -> {
            System.err.println(atm1.putMoney(account, BigDecimal.valueOf(100)));
        }).start();

        new Thread(() -> {
            System.err.println(atm1.putMoney(account, BigDecimal.valueOf(200)));
        }).start();

        ATM atm2 = new ATM(2);
        atm2.setBalance(BigDecimal.valueOf(140));
        new Thread(() -> {
            System.err.println(atm2.putMoney(account, BigDecimal.valueOf(60)));
        }).start();
    }
}
