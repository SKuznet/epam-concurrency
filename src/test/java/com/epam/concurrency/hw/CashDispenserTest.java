package com.epam.concurrency.hw;


import com.epam.concurrency.hw.ATM.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class CashDispenserTest {
    final int AMOUNT_MONEY = 1000;
    final int NUMBER_THREADS = 50;
    final int AMOUNT_MONEY_PER_THREAD = 20;
    final int DELAY_TIME = 100;

    @Test
    public void testDeposit() {
        List<BankAccount> listAccounts = new ArrayList<>();
        listAccounts.add(new BankAccount(0,0));
        Deposit depositATM = new DepositATM(listAccounts);
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Callable<Object>> listTasks = Stream.generate(() -> (Callable<Object>) () -> {
            try {
                Thread.sleep(DELAY_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            depositATM.deposit(0, AMOUNT_MONEY_PER_THREAD);
            return null;
        }).limit(NUMBER_THREADS).collect(Collectors.toList());
        try {
            executorService.invokeAll(listTasks);

            executorService.awaitTermination(200, TimeUnit.MILLISECONDS);
            assertEquals(AMOUNT_MONEY, (int)((DepositATM) depositATM).getAccountBalance(0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWithdraw() {
        List<BankAccount> listAccounts = new ArrayList<>();
        listAccounts.add(new BankAccount(AMOUNT_MONEY,1));
        Dispenser cashDispenser = new CashDispenser(listAccounts);
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Callable<Object>> listTasks = Stream.generate(() -> (Callable<Object>) () -> {
            try {
                Thread.sleep(DELAY_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cashDispenser.withdraw(1, AMOUNT_MONEY_PER_THREAD);
            return null;
        }).limit(NUMBER_THREADS).collect(Collectors.toList());
        try {
            executorService.invokeAll(listTasks);
            executorService.shutdown();

            assertEquals(0, (int) ((CashDispenser) cashDispenser).getAccountBalance(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
