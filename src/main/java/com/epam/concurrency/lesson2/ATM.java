package com.epam.concurrency.lesson2;

import java.util.concurrent.TimeUnit;

public class ATM {
    // summ in ATM
    private static int money = 100;

    private synchronized static void getMoney(int amount) {
        if(amount <= money){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            money -= amount;
            System.err.println("It is ok " + money);
        } else {
            System.err.println("Not enough money");
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
