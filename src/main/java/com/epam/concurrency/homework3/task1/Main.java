package com.epam.concurrency.homework3.task1;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i < 6; i++) {
            new Thread(new Philosopher(semaphore, i)).start();
        }
    }
}
