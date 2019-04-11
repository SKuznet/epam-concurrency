package com.epam.concurrency.homework2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {

    private static DonutDispencer donutDispencer = new DonutDispencer();

    private static List<Integer> distributedDonuts = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            futures.add(executorService.submit(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            int donutNumber = donutDispencer.getDonutNumber();
                            if (distributedDonuts.contains(donutNumber)) {
                                System.err.println("Critical error, donut have already been distributed");
                                System.exit(1);
                            } else {
                                distributedDonuts.add(donutNumber);
                                System.out.println("You are customer № " + Thread.currentThread().getId() + ". Your number is " + donutNumber);
                            }
                        }
                        System.err.println("got interrupted");
                        System.exit(0);
                    }));
        }

        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                for (Future<?> future : futures) {
                    future.cancel(true);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
        executorService.shutdown();

        System.err.println(Main.class.getCanonicalName());
    }

}
