package com.epam.concurrency.hw1;

import java.util.concurrent.TimeUnit;

public class BankAccount {
    private static int money = 100;

    //принцип туалета с ключом, который висит на двери
    private static synchronized void withdrawCash(int amount) {
        if(amount <= money) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            money -= amount;
            System.err.println("All is OK! New balance: " + money);
        } else {
            System.err.println("Not enough money");
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("Mike");
                withdrawCash(50);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("Jack");
                withdrawCash(50);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("Jack");
                withdrawCash(50);
            }
        }).start();
    }
}
