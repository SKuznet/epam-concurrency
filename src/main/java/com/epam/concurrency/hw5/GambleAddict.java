package com.epam.concurrency.hw5;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GambleAddict implements Callable<Integer> {
    private int bet;
    private int id;
    private CountDownLatch latch;

    public GambleAddict(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println("Player " + id + " is thinking...");
            TimeUnit.MILLISECONDS.sleep(150);
            bet = new Random().nextInt(37);
            System.out.println("Player " + id + " bet is " + bet);
            System.out.println("Waiting all bets");
            latch.countDown();
            return bet;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
