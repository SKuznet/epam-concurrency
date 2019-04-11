package com.epam.concurrency.homework1;

import java.math.BigDecimal;

public class BankAccount {

    private BigDecimal accountMoney;

    private int number;

    public synchronized BigDecimal getAccountMoney() {
        return accountMoney;
    }

    public BankAccount(BigDecimal accountMoney, int number) {
        this.accountMoney = accountMoney;
        this.number = number;
    }

    public synchronized String subtractMoney(BigDecimal amount) throws NotEnoughMoneyException {
        if (checkMoney(amount)) {
            accountMoney = accountMoney.subtract(amount);
            return "New balance is: " + accountMoney;
        } else {
            throw new NotEnoughMoneyException("Not enough money ");
        }
    }

    private boolean checkMoney(BigDecimal amount) {
        if (accountMoney.compareTo(amount) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
