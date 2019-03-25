package com.epam.concurrency.homework1;

import static com.epam.concurrency.homework1.Util.generateRandomMoney;

public class ATMSession extends Thread{

    @Override
    public void run() {
        ATM atm = new SimpleATM();
        atm.deposit(generateRandomMoney(), Account.account);
        atm.withdraw(generateRandomMoney(), Account.account);
    }

}