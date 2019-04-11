package com.epam.concurrency.lesson1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d;
    private int priority;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 50; i++) {
            SimplePriorities simplePriorities = new SimplePriorities();
            simplePriorities.setPriority(Thread.MIN_PRIORITY);
            executorService.execute(simplePriorities);
        }

        SimplePriorities simplePriorities = new SimplePriorities();
        simplePriorities.setPriority(Thread.MAX_PRIORITY);
        executorService.execute(simplePriorities);
        executorService.shutdown();
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            // high load
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0) {
                    Thread.yield();
                }
            }

            System.out.println(this);

            if (--countDown == 0) {
                return;
            }
        }
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ":" + countDown;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
