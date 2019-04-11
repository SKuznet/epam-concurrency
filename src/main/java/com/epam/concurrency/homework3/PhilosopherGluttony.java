package com.epam.concurrency.homework3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PhilosopherGluttony {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3, false);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Philosopher(semaphore, i));
        }

        executorService.shutdown();
    }

}

class Philosopher implements Runnable {

    private Semaphore semaphore;
    private int id;
    private int foodEaten;

    public Philosopher(Semaphore semaphore, int id) {
        this.semaphore = semaphore;
        this.id = id;
        this.foodEaten = 0;
    }

    @Override
    public void run() {

        while (foodEaten < 5) {
            try {
                semaphore.acquire();
                System.out.println("philosopher " + id + " eating");
                TimeUnit.MILLISECONDS.sleep(300);
                foodEaten++;
            } catch (InterruptedException e) {
                System.err.println("philosopher " + id + " interrupted while eating - this meal doesn't count");
            } finally {
                semaphore.release();
            }

            System.out.println("philosopher " + id + " finished meal №" + foodEaten + " and now rests");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.err.println("philosopher " + id + " interrupted while resting");
            }
        }
    }
}
