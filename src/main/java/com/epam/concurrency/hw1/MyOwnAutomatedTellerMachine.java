package com.epam.concurrency.hw1;

import java.util.concurrent.TimeUnit;

public class MyOwnAutomatedTellerMachine {
    private long totalMoney;

    public MyOwnAutomatedTellerMachine(long totalMoney){
        this.totalMoney = totalMoney;
    }

    public synchronized void getMoney(long amount){
        if(amount > totalMoney){
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("There are not enough money or ATM is empty. We are very sorry...");
        } else {
            totalMoney -= amount;
            System.out.println("Take your money and keep it hard! Your sum is " + amount);
        }
    }

    public static void main(String[] args) {
        MyOwnAutomatedTellerMachine currentAutomatedTellerMachine = new MyOwnAutomatedTellerMachine(1_000_000);

        new Thread(() -> {
            System.out.print("Very rich guy: ");
            currentAutomatedTellerMachine.getMoney(999_999);
        }).start();

        new Thread(() -> {
            System.out.print("Very poor guy: ");
            currentAutomatedTellerMachine.getMoney(1);
        }).start();

        new Thread(() -> {
            System.out.print("Very unlucky guy: ");
            currentAutomatedTellerMachine.getMoney(10_000);
        }).start();

    }


}
