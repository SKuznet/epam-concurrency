package com.epam.concurrency.lesson6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(-1);
        ResourceExample resourceExample = new ResourceExample();
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new CountThread(resourceExample, semaphore, "Resource count thread 1"));
        executorService.execute(new CountThread(resourceExample, semaphore, "Resource count thread 2"));
        executorService.execute(new CountThread(resourceExample, semaphore, "Resource count thread 3"));

        executorService.shutdown();
    }
}

class ResourceExample {
    AtomicInteger resourceCount = new AtomicInteger(0);
}

class CountThread implements Runnable {
    private ResourceExample resourceExample;
    private Semaphore semaphore;
    private String name;

    public CountThread(ResourceExample resourceExample, Semaphore semaphore, String name) {
        this.resourceExample = resourceExample;
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " waiting permission...");
            semaphore.acquire();
            resourceExample.resourceCount.set(1);

            for (int i = 0; i < 10; i++) {
                System.out.println(name + ": " + resourceExample.resourceCount.get());
                resourceExample.resourceCount.getAndIncrement();
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println(name + " release permission...");
            semaphore.release();
        }
    }
}
