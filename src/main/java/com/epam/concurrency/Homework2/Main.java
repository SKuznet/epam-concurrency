package com.epam.concurrency.Homework2;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            TimeUnit.MILLISECONDS.sleep(100);
            Thread thread = new Thread(new Client(i, new DonutMachine(i)));
            thread.start();
        }
    }
}