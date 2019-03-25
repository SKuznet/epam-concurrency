package com.epam.concurrency.homework1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ATM atm1 = new SimpleATM();
        ATMSession session1 = new ATMSession();
        ATMSession session2 = new ATMSession();
        ATMSession session3 = new ATMSession();
        Thread.sleep(3000);
        session1.start();
        Thread.sleep(3000);
        session2.start();
        Thread.sleep(3000);
        session3.start();
        Thread.sleep(3000);
        System.out.println("Final balance in ATM is " + atm1.getBalance(Account.account));
    }
}