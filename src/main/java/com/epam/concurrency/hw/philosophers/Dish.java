package com.epam.concurrency.hw.philosophers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Dish {
    static Semaphore semaphore = new Semaphore(3);

    static public void eat(int dishNumber) {
        try {
            semaphore.acquire();
            System.err.println("Philosopher from thread " + Thread.currentThread().getName() + " is eating" +
                    " his " + dishNumber + " dish");
            TimeUnit.MILLISECONDS.sleep(200);
            System.err.println("Philosopher from thread " + Thread.currentThread().getName() + " finished eating" +
                    " his " + dishNumber + " dish");
            System.err.println("    semaphore.getQueueLength()" + semaphore.getQueueLength());

        } catch (InterruptedException e) {
            System.err.println("Philosopher from thread " + Thread.currentThread().getName() + " is interrupter" +
                    " while eating!");
        }
        finally {
            semaphore.release();
        }
    }
}
