package com.epam.concurrency.homework1;

import java.math.BigDecimal;

public class SimpleATM implements ATM {

    public void withdraw(BigDecimal amount, Account account) {
        account.withdraw(amount);
    }

    public void deposit(BigDecimal amount, Account account) {
        account.deposit(amount);
    }

    public BigDecimal getBalance(Account account) {
        return account.getBalance();
    }

}