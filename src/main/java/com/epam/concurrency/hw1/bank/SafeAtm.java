package com.epam.concurrency.hw1.bank;

import java.math.BigDecimal;

public class SafeAtm implements Atm {

    @Override
    public BigDecimal withdraw(Account account, BigDecimal sumToWithdraw) {
        if (account == null || sumToWithdraw == null) {
            throw new IllegalArgumentException("account or sumToWithdraw is null");
        }
        synchronized (account) {
            BigDecimal accountSum = account.getSum();
            if (accountSum.doubleValue() < 0) {
                throw new NegativeBalanceException("balance is less than zero.");
            }
            if (accountSum.compareTo(sumToWithdraw) < 0) {
                throw new LowBalanceException("not enough money no retrieve.");
            }
            account.setSum(accountSum.subtract(sumToWithdraw));
            return accountSum;
        }
    }

    @Override
    public BigDecimal putMoney(Account account, BigDecimal sumToPut) {
        if (account == null || sumToPut == null) {
            throw new IllegalArgumentException("account or sumToWithdraw is null");
        }
        synchronized (account) {
            if (sumToPut.compareTo(new BigDecimal(0)) < 0) {
                throw new IllegalArgumentException("sum to put should be positive");
            }
            BigDecimal accountSum = account.getSum();
            account.setSum(accountSum.add(sumToPut));
            return accountSum;
        }
    }
}
