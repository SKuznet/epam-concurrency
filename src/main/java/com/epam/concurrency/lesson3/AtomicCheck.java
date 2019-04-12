package com.epam.concurrency.lesson3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicCheck implements Runnable {

    private int i = 0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicCheck atomicCheck = new AtomicCheck();

        executorService.execute(atomicCheck);

        while (true) {
            int val = atomicCheck.getValue();

            if (val % 2 != 0) {
                System.err.println(val);
                System.exit(0);
            }
        }
    }

    public int getValue() {
        return i;
    }

    public void evenIncrement() {
        i = i * 2;
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

}