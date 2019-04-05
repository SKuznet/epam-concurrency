package com.epam.concurrency.hw3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i < 6; i++) {
            new Philosopher(semaphore, i).start();
        }
    }
}

