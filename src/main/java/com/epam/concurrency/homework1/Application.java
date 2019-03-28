package com.epam.concurrency.homework1;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {

    private static long WITHDRAW_AMOUNT = 250;

    public static void main(String[] args) throws InterruptedException {
        Atm atm = new Atm();
        atm.setMoney(BigDecimal.valueOf(1000L));

        System.err.println("Preparing:");
        System.err.println(System.lineSeparator());

        System.err.println(atm);
        System.err.println(System.lineSeparator());

        System.err.println("Run!");
        System.err.println(System.lineSeparator());

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                Human human = new Human();
                human.setCash(BigDecimal.ZERO);
                human.setCardBalance(BigDecimal.valueOf(WITHDRAW_AMOUNT));

                System.err.println("Human before withdraw:");
                System.err.println(human);

                human.withdraw(BigDecimal.valueOf(WITHDRAW_AMOUNT), atm);

                System.err.println("Human after withdraw:");
                System.err.println(human);
                System.err.println(System.lineSeparator());

                System.err.println("Atm status:");
                System.err.println(atm);
                System.err.println(System.lineSeparator());
            });
            TimeUnit.SECONDS.sleep(1);
        }

        TimeUnit.SECONDS.sleep(1);
        System.err.println("Atm after run:");
        System.err.println(atm);

        executorService.shutdown();
    }
}
