package com.epam.concurrency.lesson4;

import java.util.concurrent.TimeUnit;

public class InterruptedExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println(Thread.currentThread().isInterrupted());

                for (int i = 0; i < 1000; i++) {
                    System.err.println(i);
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        System.err.println("Interrupted!");
                        break;
                    }
                }
            }
        });

        System.err.println("Started..");
        thread.start();

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //don't use this
        //thread.stop();
        thread.interrupt();

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("Finish");
    }
}
