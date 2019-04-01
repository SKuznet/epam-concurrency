package com.epam.concurrency.lesson5.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInterruptableExample {

    private static ReentrantLock lock = new ReentrantLock();
    private boolean interupptable;

    public ReentrantLockInterruptableExample() {
        this(true);
    }

    public ReentrantLockInterruptableExample(boolean interupptable) {
        this.interupptable = interupptable;
    }

    public static void main(String[] args) {
        ReentrantLockInterruptableExample example = new ReentrantLockInterruptableExample();
        example.lockAndInterrupt();

    }

    private void lockAndInterrupt() {
        Thread firstThread = new Thread(new AcquireLockRunnable(1, lock, interupptable), "Thread(1)");
        firstThread.start();

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread[] others = new Thread[6];

        for (int i = 2; i < 8; i++) {
            others[i - 2] = new Thread(new AcquireLockRunnable(i, lock, interupptable), "Thread(" + i + ")");
            others[i - 2].start();
        }

        System.err.println("Interrupt threads");

        for (int i = 0; i < 6; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(500 * i / 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.err.println("Interrupt " + others[i].getName());
            others[i].interrupt();
        }
    }

}
