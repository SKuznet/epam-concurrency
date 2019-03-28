package com.epam.concurrency.hw2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new PonchikEater());
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println("All served?");
    }
}
