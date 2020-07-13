package com.epam.concurrency.homework1;

import java.math.BigDecimal;

public class Atm {

    private volatile BigDecimal money;

    public void add(BigDecimal amount) {
        synchronized (this) {
            money = money.add(amount);
        }
    }

    public boolean withdraw(BigDecimal amount) {
        synchronized (this) {
            if (money.compareTo(amount) >= 0) {
                money = money.subtract(amount);
                return true;
            } else {
                System.err.println("Sorry, not enough money in the ATM");
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return "Atm money: " + money;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
