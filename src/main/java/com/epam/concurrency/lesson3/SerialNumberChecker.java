package com.epam.concurrency.lesson3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SerialNumberChecker {
    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable {

        @Override
        public void run() {
            while (true) {
                int serial = SerialNumberGenerator.nextSerialNumber();

                if (serials.contains(serial)) {
                    System.err.println("Duplicates: " + serial);
                    System.exit(0);
                }

                serials.add(serial);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new SerialChecker());
        }
        executorService.shutdown();

        if (args.length > 0) {
            try {
                TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
                System.out.println("No duplicates detected!");
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
