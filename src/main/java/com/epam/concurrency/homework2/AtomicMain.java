package com.epam.concurrency.homework2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicMain {

    public static void main(String[] args) {
        AtomicIntegerExperiment atomicIntegerExperiment = new AtomicIntegerExperiment();
        atomicIntegerExperiment.runExecutorService();
    }

    static class AtomicIntegerExperiment {
        private AtomicInteger atomicInteger;
        private ExecutorService executorService;

        public AtomicIntegerExperiment() {
            this.atomicInteger = new AtomicInteger(0);
            this.executorService = Executors.newFixedThreadPool(5);
        }

        public void runExecutorService() {
            IntStream.range(0, 1000).forEach(i -> executorService.submit(() -> {
                System.out.println("Thread name:  " + Thread.currentThread().getName() +
                        " increment and get: " + atomicInteger.incrementAndGet());
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
            executorService.shutdown();
        }
    }

}