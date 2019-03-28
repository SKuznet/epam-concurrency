package com.epam.concurrency.hw.ATM;

import com.epam.concurrency.hw.BankAccount;

import java.util.List;

public class DepositATM extends AbstractATM implements Deposit {
    public DepositATM(List<BankAccount> bankAccounts) {
        super.bankAccounts.addAll(bankAccounts);
    }

    @Override
    public void deposit(int accountId, int moneyAmount) {
        BankAccount bankAccount = findAccount(accountId);
        synchronized (bankAccount) {
            bankAccount.deposit(moneyAmount);
        }
    }
}
