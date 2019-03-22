package com.epam.concurrency.hw1;

import java.util.concurrent.TimeUnit;

public class ATM {
    // сумма в банкомате
    private static int money = 100;

    private synchronized static void getMoney(int amount) {
        if (amount <= money) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            money-=amount;
            System.err.println("All OK! New balance: " + money);
        } else {
            System.err.println("no enough money");
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("Mike");
                getMoney(50);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("Jack");
                getMoney(50);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("Bart");
                getMoney(50);
            }
        }).start();

    }
}
