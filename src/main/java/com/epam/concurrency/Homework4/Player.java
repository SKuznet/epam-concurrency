package com.epam.concurrency.Homework4;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Player implements Runnable{

    private static CyclicBarrier cyclicBarrier;

    private int id;

    private int betNumber;

    public Player(CyclicBarrier barrier, int id) {
        this.id = id;
        cyclicBarrier = barrier;
    }

    public synchronized int getBetNumber() {
        return betNumber;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    bet();
                }
                cyclicBarrier.await();
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public int bet() {
        Random random = new Random();
        betNumber = random.nextInt(37);
        return betNumber;
    }
}
