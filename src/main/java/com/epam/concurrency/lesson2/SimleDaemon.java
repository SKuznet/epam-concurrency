package com.epam.concurrency.lesson2;

import java.util.concurrent.TimeUnit;

public class SimleDaemon implements Runnable {
    public static void main(String[] args) {
        long before = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimleDaemon());
            daemon.setDaemon(true);
            daemon.start();
        }

        System.out.println("All daemons is ran");

        long total = System.currentTimeMillis() - before;
        System.out.println("Time of 1st part: " + total);

        try {
            TimeUnit.MILLISECONDS.sleep(175);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            } catch (InterruptedException e) {
                System.err.println("sleep() interrupted");
            }
        }
    }
}
