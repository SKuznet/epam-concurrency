package com.epam.concurrency.hw.ATM.donuts;

import com.epam.concurrency.lesson1.FixedTreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(11);

        for (int i = 0; i < 10; ++i) {
            executorService.submit(new Cop());
        }
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
        System.err.println("Finished");
    }
}
