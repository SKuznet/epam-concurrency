package com.epam.concurrency.Homework2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<DonnutAutomat> donnutAutomats = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            donnutAutomats.add(new DonnutAutomat());
        }

        while(true) {
            donnutAutomats.forEach(a -> {
                new Client(a.call()).run();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
