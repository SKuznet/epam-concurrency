package com.epam.concurrency.Homework1;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class ATM implements Runnable {
    private static BigDecimal moneyAtATM = BigDecimal.valueOf(1000);
    private int atmNumber;

    public ATM(int atmNumber) {
        this.atmNumber = atmNumber;
    }

    public String getMoney(BigDecimal amount, BankAccount bankAccount) throws InterruptedException {

        if (moneyAtATM.compareTo(amount) > 0) {
            try {
                bankAccount.subtractMoney(amount);
                moneyAtATM = moneyAtATM.subtract(amount);
                return ("It's all OK! ATM: " + this.atmNumber + "! New balance: " + bankAccount.getAccountMoney());
            } catch (NotEnoughMoneyException e) {
                return (e.getMessage() + "ATM:" + atmNumber);
            }
        } else {
            return ("Not enough money in this ATM: " + this.atmNumber);
        }
    }

    public String addMoney(BigDecimal amount, BankAccount bankAccount) {
        return bankAccount.addMoney(amount);
    }

    @Override
    public void run() {
    }
}
