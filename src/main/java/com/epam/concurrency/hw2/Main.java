package com.epam.concurrency.hw2;

import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static int count;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        TreeSet<Integer> numbers = new TreeSet<>();

        for (int i = 0; i < 10; i++) {
            while (!Thread.currentThread().isInterrupted()) {
                executorService.submit(() -> {
                    int ticketNumber = count++;
                    if (!numbers.add(ticketNumber)) {
                        System.err.println("Duplicates: " + ticketNumber);
                        System.exit(0);
                    }
                });
            }
        }

        try {
            Thread.sleep(10);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
