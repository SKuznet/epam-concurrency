package com.epam.concurrency.hw.ATM;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractATM {
    List<BankAccount> bankAccounts = new ArrayList<>();
    
    public Integer getAccountBalance(int accountId) {
        return findAccount(accountId).getMoneyAmount();
    }

    protected BankAccount findAccount(int accountId) {
        Optional<BankAccount> optionalBankAccount = bankAccounts.stream()
                .filter(e -> e.getAccountId() == accountId)
                .findFirst();
        return optionalBankAccount.orElseThrow(() -> new IllegalArgumentException("Wrong accountId"));
    }
}
