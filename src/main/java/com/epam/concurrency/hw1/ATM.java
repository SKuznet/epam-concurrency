package com.epam.concurrency.hw1;

import java.util.concurrent.atomic.AtomicInteger;

public class ATM {
    private static AtomicInteger totalMoney = new AtomicInteger(100);

    private static void getMoney(int amount) {
        if (totalMoney.getAndAdd(-amount) >= amount) {
            System.err.println("All ok! New Balance: " + totalMoney);
        } else {
            System.err.println("No enough money!");
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            getMoney(50);
        }).start();

        new Thread(() -> {
            getMoney(50);
        }).start();

        new Thread(() -> {
            getMoney(50);
        }).start();
    }
}

