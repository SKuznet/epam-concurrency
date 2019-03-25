package com.epam.concurrency.lesson1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepingTaskPractise extends SpellCast {

    @Override
    public void run() {
        try {
            while (countDown-- > 0) {
                System.out.println(getStatus());
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new SleepingTaskPractise());
        }
        executorService.shutdown();
    }
}
