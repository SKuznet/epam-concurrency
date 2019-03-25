package com.epam.concurrency.homework1;

import java.math.BigDecimal;

public class Account {

    public static final Account account = new Account();
    private BigDecimal balance = new BigDecimal(20000);

    private Account() {
    }

    synchronized public void withdraw(BigDecimal amount) {
        if (amount.signum() < 0) {
            throw new RuntimeException("Wrong amount. Please, input amount more then 0");
        }
        if (account.getBalance().subtract(amount).signum() < 0) {
            throw new RuntimeException("Wrong amount. Please, input amount more then 0");
        }

        balance = balance.subtract(amount);
        System.out.println("Thread  " + Thread.currentThread().getId() + "" + " withdraw " + amount + " and our balance " + balance);
    }

    synchronized public void deposit(BigDecimal amount) {
        if (amount.signum() < 0) {
            throw new RuntimeException("Wrong amount. Please, input amount more then 0");
        }
        balance = balance.add(amount);
        System.out.println("Thread  " + Thread.currentThread().getId() + "" + " deposit " + amount + " and our balance " + balance);
    }

    public synchronized BigDecimal getBalance() {
        return balance;
    }

}