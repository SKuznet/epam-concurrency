package com.epam.concurrency.homework3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
    private Semaphore semaphore;
    private int foodEatingCount;
    private int philosopherId;

    public Philosopher(Semaphore semaphore, int philosopherId) {
        this.semaphore = semaphore;
        this.philosopherId = philosopherId;
        this.foodEatingCount = 0;
    }

    @Override
    public void run() {
        while (foodEatingCount < 5) {
            try {
                semaphore.acquire();
                System.err.println("Philosopher " + philosopherId + " are eating...");
                TimeUnit.MILLISECONDS.sleep(100);
                foodEatingCount++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            System.err.println("Philosopher " + philosopherId + " finish meal.");
        }
    }
}
