package com.epam.concurrency.hw1.bank;

import java.math.BigDecimal;

public interface Atm {

    /**
     * @param account account to talk to.
     * @param sumToWithdraw amount to withdraw.
     * @return sum that left on the account.
     */
    BigDecimal withdraw(Account account, BigDecimal sumToWithdraw);

    /**
     * @param account account to talk to.
     * @param sumToPut amount to be added to account sum
     * @return sum that left on the account.
     */
    BigDecimal putMoney(Account account, BigDecimal sumToPut);
}
