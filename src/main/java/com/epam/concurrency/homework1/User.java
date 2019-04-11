package com.epam.concurrency.homework1;

import java.math.BigDecimal;

public class User {

    private BigDecimal amount;
    private String name;

    public User(BigDecimal amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }
}
