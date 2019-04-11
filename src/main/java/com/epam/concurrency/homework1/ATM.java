package com.epam.concurrency.homework1;

import java.math.BigDecimal;

public class ATM {
    private static BigDecimal moneyAtATM = BigDecimal.valueOf(1000);
    private int atmNumber;

    public ATM(int atmNumber) {
        this.atmNumber = atmNumber;
    }

    public synchronized String getMoney(BigDecimal amount, BankAccount bankAccount) {

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
}
