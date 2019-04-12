package com.epam.concurrency.lesson6;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        System.out.println("Starting of thread...");

        new ExampleThread(latch);

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(latch.getCount());
        System.out.println("Finished");
        new ExampleThread(latch);
    }

}

class ExampleThread implements Runnable {
    private CountDownLatch latch;

    public ExampleThread(CountDownLatch latch) {
        this.latch = latch;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            System.out.println("latch count " + latch.getCount());
            latch.countDown();
        }
    }
}
