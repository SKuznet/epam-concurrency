package com.epam.concurrency.hw3;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PhilosopherHomework {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i < 6; i++) {
            new Philosopher(semaphore, i).start();
        }
    }
}

class Philosopher extends Thread {
    private Semaphore semaphore;
    private int numberOfDinners = 0;
    private int id;
    private int delay;

    public Philosopher(Semaphore semaphore, int philosopherId) {
        this.semaphore = semaphore;
        this.id = philosopherId;
        this.delay = new Random().nextInt(300) + 100;
    }

    @Override
    public void run() {
        while (numberOfDinners < 5) {
            try {
                semaphore.acquire();
                System.out.println("Philosopher " + id + " started to eat");
                TimeUnit.MILLISECONDS.sleep(delay);
                numberOfDinners++;

                System.out.println("Philosopher " + id + " stand up from the table");
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.err.println("Someone has interrupted " + id + " philosopher's dinner");
            } finally {
                semaphore.release();
            }
        }
        System.err.println("Philosopher " + id + " is well-fed");
    }
}
