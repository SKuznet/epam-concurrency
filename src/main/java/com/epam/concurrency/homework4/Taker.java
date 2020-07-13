package com.epam.concurrency.homework4;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Taker implements Runnable {

    private Exchanger<String> exchanger;

    public Taker(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                String taken = exchanger.exchange("", 5, TimeUnit.SECONDS);
                System.err.println("Exchange committed! Taken: " + taken);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.err.println("Oops, nothing to take anymore!");
            }
        }
    }
}
