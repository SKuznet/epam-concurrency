package com.epam.concurrency.hw1;

import com.epam.concurrency.hw1.bank.Account;
import com.epam.concurrency.hw1.bank.Atm;

import java.math.BigDecimal;

public class Person {

    private Account account;
    private Atm atm;
    private BigDecimal pocket;

    public Person(Account account) {
        this.account = account;
        pocket = new BigDecimal(0);
    }

    public void putMoney(BigDecimal sum) {
        atm.putMoney(account, sum);
    }

    public void withdrawMoney(BigDecimal sum) {
        atm.withdraw(account, sum);
        pocket = pocket.add(sum);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    public BigDecimal getPocket() {
        return pocket;
    }

    public void setPocket(BigDecimal pocket) {
        this.pocket = pocket;
    }
}
