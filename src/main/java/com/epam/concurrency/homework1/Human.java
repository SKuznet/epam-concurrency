package com.epam.concurrency.homework1;

import java.math.BigDecimal;

public class Human {

    private static int humanCounter = 0;

    private int humanCode;

    private BigDecimal cash;

    private BigDecimal cardBalance;

    public Human() {
        humanCode = humanCounter++;
    }

    public void deposit(BigDecimal amount, Atm atm) {
        if (cash.compareTo(amount) >= 0) {
            atm.add(amount);
            cash = cash.subtract(amount);
            cardBalance = cardBalance.add(amount);
        } else {
            System.err.println("Not enough cash to deposit.");
        }
    }

    public void withdraw(BigDecimal amount, Atm atm) {
        if (cardBalance.compareTo(amount) >= 0) {
            if (atm.withdraw(amount)) {
                cardBalance = cardBalance.subtract(amount);
                cash = cash.add(amount);
            }
        } else {
            System.err.println("Not enough money on card");
        }
    }

    @Override
    public String toString() {
        return "Human: " + humanCode + System.lineSeparator() +
                "Cash: " + cash + System.lineSeparator() +
                "Card Balance: " + cardBalance;
    }

    public int getHumanCode() {
        return humanCode;
    }

    public void setHumanCode(int humanCode) {
        this.humanCode = humanCode;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(BigDecimal cardBalance) {
        this.cardBalance = cardBalance;
    }
}
