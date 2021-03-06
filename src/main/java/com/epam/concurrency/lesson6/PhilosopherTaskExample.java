package com.epam.concurrency.lesson6;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PhilosopherTaskExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        for (int i = 1; i < 6; i++) {
            new Philosopher(semaphore, i).start();
        }
    }
}

class Philosopher extends Thread {
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
            while (foodEatingCount < 1) {
                if (philosopherId == 2) {
                    TimeUnit.MILLISECONDS.sleep(100);
                } else if (philosopherId == 3) {
                    TimeUnit.MILLISECONDS.sleep(50);
                } else if (philosopherId == 4) {
                    TimeUnit.MILLISECONDS.sleep(50);
                } else if (philosopherId == 5) {
                    TimeUnit.MILLISECONDS.sleep(100);
                }
                semaphore.acquire();
                System.out.println("Philosopher " + philosopherId + " sit at the table...");
                TimeUnit.MILLISECONDS.sleep(500);
                foodEatingCount++;

                System.out.println("Philosopher " + philosopherId + " stand up from the table...");
                semaphore.release();

                // philosopher is walking...
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
