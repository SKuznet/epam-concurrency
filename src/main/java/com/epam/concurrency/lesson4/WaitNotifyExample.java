package com.epam.concurrency.lesson4;

import java.util.concurrent.TimeUnit;

public class WaitNotifyExample {
    private static final Object object = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.err.println(i);

                    if (i == 5) {
                        synchronized (object) {
                            object.notifyAll();
                        }
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.err.println("Starting...");
        thread.start();
        synchronized (object) {
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.err.println("Finish");
    }
}
