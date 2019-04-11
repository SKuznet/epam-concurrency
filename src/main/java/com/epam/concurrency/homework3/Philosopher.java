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
    }

    @Override
    public void run() {
        try {
            while (foodEatingCount < 5) {
                semaphore.acquire();
                System.out.println("Philosopher №" + philosopherId + " start eating for " + (foodEatingCount + 1) + " time.");
                TimeUnit.MILLISECONDS.sleep(500);
                foodEatingCount++;
                System.out.println("Philosopher №" + philosopherId + " is walking.");
                semaphore.release();
                TimeUnit.MILLISECONDS.sleep(500);
                if(foodEatingCount == 5){
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println("\nPhilosopher № " + philosopherId + " is HAPPY\n");
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
