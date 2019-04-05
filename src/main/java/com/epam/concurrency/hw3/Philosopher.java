package com.epam.concurrency.hw3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class Philosopher extends Thread {
    private Semaphore semaphore;
    private int foodEatingCount;
    private int id;

    public Philosopher(Semaphore semaphore, int id) {
        this.semaphore = semaphore;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (foodEatingCount < 5) {
                semaphore.acquireUninterruptibly();
                System.out.println("Philosopher " + id + " sit at the table...");
                TimeUnit.MILLISECONDS.sleep(500);
                foodEatingCount++;

                System.out.println("Philosopher " + id + " stand up from the table...");
                semaphore.release();

                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
