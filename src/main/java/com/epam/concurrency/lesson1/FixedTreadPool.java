package com.epam.concurrency.lesson1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedTreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new SpellCast());
        }
        executorService.shutdown();
    }
}
