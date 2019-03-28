package com.epam.concurrency.hw;

public class BankAccount {
    private Integer accountId;
    Integer moneyAmount;
    
    public BankAccount(Integer moneyAmount, int accountId) {
        this.accountId = accountId;
        this.moneyAmount = moneyAmount > 0 ? moneyAmount : 0;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Integer getMoneyAmount() {
        return moneyAmount;
    }
    
    public boolean withdraw(int moneyAmount) {
        if (this.moneyAmount < moneyAmount) {
            return false;
        } else {
            this.moneyAmount -= moneyAmount;
            return true;
        }
    }

    public void deposit(int moneyAmount) {
        this.moneyAmount += moneyAmount;
    }
}
