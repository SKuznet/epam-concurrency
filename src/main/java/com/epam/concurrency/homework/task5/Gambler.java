package com.epam.concurrency.homework.task5;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Gambler implements Callable<Integer> {

    private CountDownLatch countDownLatch;
    private int id;
    private int bet;

    public Gambler(CountDownLatch countDownLatch, int id) {
        this.countDownLatch = countDownLatch;
        this.id = id;
    }

    @Override
    public Integer call() {
        try {
            System.out.println("gambler #" + id);
            TimeUnit.MILLISECONDS.sleep(150);

            Integer currentBet = setAndReturnBet();

            System.out.println("his choice: " + currentBet + "!");

            countDownLatch.countDown();

            return currentBet;

        } catch (InterruptedException exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    public Integer getId() {
        return id;
    }

    public Integer setAndReturnBet() {
        bet = new Random().nextInt(37);
        return bet;
    }
}
