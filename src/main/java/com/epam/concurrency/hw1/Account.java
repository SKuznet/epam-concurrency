package com.epam.concurrency.hw1;

import java.math.BigDecimal;

public class Account {
    private int id;
    private BigDecimal sum;

    public Account(int id, BigDecimal sum) {
        this.id = id;
        this.sum = sum;
    }

    public synchronized BigDecimal getSum() {
        return sum;
    }

    public synchronized BigDecimal substractMoney(BigDecimal amount) throws NotEnoughMoneyException {
        if (sum.compareTo(amount) >= 0) {
            sum = sum.subtract(amount);
            return sum;
        } else {
            throw new NotEnoughMoneyException("Not enough money");
        }
    }
}
