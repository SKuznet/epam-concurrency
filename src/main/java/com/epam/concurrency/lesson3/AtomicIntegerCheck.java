package com.epam.concurrency.lesson3;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCheck implements Runnable {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public int getValue() {
        return atomicInteger.get();
    }

    public void evenIncrement() {
        atomicInteger.addAndGet(2);
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 5000);

        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicIntegerCheck atomicIntegerCheck = new AtomicIntegerCheck();
        executorService.execute(atomicIntegerCheck);
        executorService.shutdown();

        while (true) {
            int val = atomicIntegerCheck.getValue();
            if (val%2 != 0) {
                System.err.println("broken with:" + val);
                System.exit(0);
            }
        }
    }
}
