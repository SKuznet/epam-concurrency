package com.epam.concurrency.homework6;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        RandomCSVWriter randomCSVWriter = new RandomCSVWriter();
        ExecutorService executorForWiters = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorForWiters.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        randomCSVWriter.createCsvFiles(50);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorForWiters.shutdown();
    }
}
