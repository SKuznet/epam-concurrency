package com.epam.concurrency.homework;

import java.util.concurrent.TimeUnit;

public class ATM {
    private static volatile int atmMoney = 100;

    private static synchronized int getMoneyFromAtm(int count, String name) {
        if (count <= atmMoney) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atmMoney -= count;
            System.err.println(name + " successfully get " + count + " money");
            return count;
        } else {
            System.err.println(name + " did not get money: not enough money in ATM");
            return 0;
        }
    }

    public static void main(String[] args) {
        System.err.println("ATM money:" + ATM.atmMoney);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                getMoneyFromAtm(50, "Mike");
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                getMoneyFromAtm(50, "Jack");
            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                getMoneyFromAtm(50, "Petr");
            }
        });
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.err.println("ATM money:" + ATM.atmMoney);
    }
}
