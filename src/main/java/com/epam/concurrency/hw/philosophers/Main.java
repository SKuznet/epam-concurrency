package com.epam.concurrency.hw.philosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        nonInterrupterPhilosophers();
        interruptedPhilosophers();
    }
    private static void nonInterrupterPhilosophers() {
        ExecutorService executorService = defaultPhilosophers();
        executorService.shutdown();
    }
    private static void interruptedPhilosophers() {
        ExecutorService executorService = defaultPhilosophers();
        while (!executorService.isTerminated()) {
            executorService.shutdownNow();
        }
    }

    private static ExecutorService defaultPhilosophers() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Philosopher());
        }
        return executorService;
    }
}
