package com.epam.concurrency.hw1.bank;

import java.math.BigDecimal;

public class Account {

    private BigDecimal sum;

    public Account(BigDecimal sum) {
        this.sum = sum;
    }

    BigDecimal getSum() {
        return sum;
    }

    void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public double onlyForStuffUseMethod(){
        return getSum().doubleValue();
    }
}
