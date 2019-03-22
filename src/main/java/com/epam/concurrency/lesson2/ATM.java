package com.epam.concurrency.lesson2;

import java.util.concurrent.TimeUnit;

public class ATM {

    //all money in ATM
    private static int money = 100;

    //printsip tualeta s kluchom
    private synchronized static void getMoney(int amount) {
        if (amount <= money) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            money -= amount;
            System.err.println("All OK! New balance: " + money);
        } else {
            System.err.println("Not enough money");
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
                System.err.println("Petr");
                getMoney(50);
            }
        }).start();

    }
}
