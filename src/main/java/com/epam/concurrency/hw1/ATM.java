package com.epam.concurrency.hw1;

import java.math.BigDecimal;

public class ATM implements Runnable{
    private int id;
    private static BigDecimal balance;

    public ATM(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String putMoney(Account account, BigDecimal amount) {
        synchronized (Account.class) {
            if (balance.compareTo(amount) > 0) {
                try {
                    account.substractMoney(amount);
                    balance = balance.subtract(amount);
                    return ("ATM #" + this.id + " balance: " + account.getSum());
                } catch (NotEnoughMoneyException e) {
                    return (e.getMessage() + "ATM #" + id);
                }
            } else {
                return ("Not enough money");
            }
        }
    }

    @Override
    public void run() {

    }
}
