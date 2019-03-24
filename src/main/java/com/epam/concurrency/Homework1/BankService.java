package com.epam.concurrency.Homework1;

import java.math.BigDecimal;

public class BankService {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(BigDecimal.valueOf(100), 1);

        ATM atm = new ATM(0);
        new Thread(() -> {
            try {
                System.err.println(atm.getMoney(BigDecimal.valueOf(50), bankAccount));
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }).start();

        ATM atm1 = new ATM(1);
        new Thread(() -> {
            try {
                System.err.println(atm1.getMoney(BigDecimal.valueOf(50), bankAccount));
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }).start();

        ATM atm2 = new ATM(2);
        new Thread(() -> {
            try {
                System.err.println(atm2.getMoney(BigDecimal.valueOf(50), bankAccount));
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }
}
