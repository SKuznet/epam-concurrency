package com.epam.concurrency.homework3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 1; i < 6; i++) {
            executorService.execute(new Philosopher(semaphore, i));
        }
        executorService.shutdown();
    }
}
