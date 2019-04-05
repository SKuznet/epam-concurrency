package com.epam.concurrency.homework3.task2;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Giver implements Runnable {

    private Exchanger<String> exchanger;
    private char startingChar = 'A';

    public Giver(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        StringBuilder stringBuilder = new StringBuilder(startingChar);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                stringBuilder.append(startingChar++);
            }
            try {
                String given = exchanger.exchange(stringBuilder.toString());
                System.err.println("Exchange committing...");
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
