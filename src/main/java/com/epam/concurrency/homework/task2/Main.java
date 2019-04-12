package com.epam.concurrency.homework.task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Dispenser dispenser = new Dispenser();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 200; i++) {
            executorService.execute(dispenser);
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("5 seconds left");
                System.exit(0);
        }
        });

    }

}
