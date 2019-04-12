package com.epam.concurrency.homework3;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {

    private Semaphore semaphore;
    private int hungriness;
    private int id;
    private boolean isVeryHungry;

    public Philosopher(Semaphore semaphore, int id) {
        this.semaphore = semaphore;
        this.id = id;
    }

    @Override
    public void run() {
        while (hungriness < 5) {
            int takenPermits = 0;
            try {
                Random random = new Random();
                isVeryHungry = random.nextBoolean();
                if (isVeryHungry) {
                    takenPermits = 2;
                    System.err.println("Philosopher " + id + " is very hungry!");
                    semaphore.acquire(takenPermits);
                } else {
                    semaphore.acquireUninterruptibly(takenPermits);
                }
                System.err.println("Philisopher " + id + " starts to eat");
                hungriness++;
                System.err.println("Philosopher " + id + " has eaten " + hungriness + " times");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.err.println("Some has interrupted philosoper " + id);
            } finally {
                if (isVeryHungry) {
                    semaphore.release(takenPermits);
                } else {
                    semaphore.release();
                }
            }
        }
    }
}
