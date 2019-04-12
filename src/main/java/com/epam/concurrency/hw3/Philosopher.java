package com.epam.concurrency.hw3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable{
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
                semaphore.acquire();
                System.out.println("Philosopher №" + id + " start eating for " + (foodEatingCount + 1) + " time.");
                TimeUnit.MILLISECONDS.sleep(500);
                foodEatingCount++;
                System.out.println("Philosopher №" + id + " is walking.");
                semaphore.release();
                TimeUnit.MILLISECONDS.sleep(500);
                if(foodEatingCount == 5){
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println("Philosopher № " + id + " is finished");
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
