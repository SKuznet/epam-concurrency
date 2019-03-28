package com.epam.concurrency.lesson1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new SpellCast());
        }
        System.out.println("waiting...");
        executorService.shutdown();
    }
}