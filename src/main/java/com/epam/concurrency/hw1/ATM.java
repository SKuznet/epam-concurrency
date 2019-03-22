package com.epam.concurrency.hw1;

import java.util.concurrent.TimeUnit;

public class ATM {
    private static int totalMoney = 100;

    private synchronized static void getMoney(int amount) {
        if (totalMoney >= amount) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            totalMoney -= amount;
            System.err.println("All ok! New Balance: " + totalMoney);
        } else {
            System.err.println("No enough money!");
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            System.err.println("Mike");
            getMoney(50);
        }).start();

        new Thread(() -> {
            System.err.println("Jack");
            getMoney(50);
        }).start();

        new Thread(() -> {
            System.err.println("Petr");
            getMoney(50);
        }).start();
    }
}

