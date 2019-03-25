package com.epam.concurrency.homework1;

import java.math.BigDecimal;

public interface ATM {

    void withdraw(BigDecimal amount, Account account);

    void deposit(BigDecimal amount, Account account);

    BigDecimal getBalance(Account account);

}