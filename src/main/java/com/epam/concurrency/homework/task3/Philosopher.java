package com.epam.concurrency.homework.task3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Philosopher extends Thread{

    private Semaphore semaphore;
    private int count;
    private int id;

    public Philosopher(Semaphore semaphore, int id) {
        this.semaphore = semaphore;
        this.id = id;
    }

    @Override
    public void run() {
        while (count < 5) {
            try {
                semaphore.acquire();

                System.out.println("Philosopher " + id + " starts");
                TimeUnit.MILLISECONDS.sleep(500);
                count++;

                System.out.println("Philosopher " + id + " finished" + (count == 5? " for today": ""));

                semaphore.release();

                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
