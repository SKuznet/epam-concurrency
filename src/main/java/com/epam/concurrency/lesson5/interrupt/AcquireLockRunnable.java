package com.epam.concurrency.lesson5.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AcquireLockRunnable implements Runnable {
    private int id;
    private boolean isInterrupt;
    private ReentrantLock lock;

    public AcquireLockRunnable(int id, ReentrantLock lock) {
        this(id, lock, true);
    }

    public AcquireLockRunnable(int id, ReentrantLock lock, boolean isInterrupt) {
        this.id = id;
        this.isInterrupt = isInterrupt;
        this.lock = lock;
    }

    private static void print(String text) {
        System.err.println(Thread.currentThread().getName() + ": " + text);
    }

    @Override
    public void run() {
        print("Trying to lock...");
        try {
            if (isInterrupt) {
                lock.lockInterruptibly();
            } else {
                lock.lock();
            }
        } catch (InterruptedException e) {
            print("Acquiring lock failed dut to " + e);
            return;
        }

        print("Got lock(" + id + ")");

        try {
            try {
                if (id == 1) {
                    TimeUnit.SECONDS.sleep(3);
                } else {
                    TimeUnit.MILLISECONDS.sleep(2500);
                }
            } catch (InterruptedException e) {
                print("Sleep interrupted");
            }
        } finally {
            lock.unlock();
            print("Unlocked(" + id + ")");
        }
    }

}
