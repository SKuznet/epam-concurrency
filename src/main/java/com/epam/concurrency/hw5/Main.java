package com.epam.concurrency.hw5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        int playerNumber = 4;
        Rullete rullete = new Rullete(playerNumber);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.submit(rullete);
        for (int i = 1; i < playerNumber + 1; i++) {
            threadPool.submit(new Player(i, rullete));
        }

        //roulette works only for 20 seconds
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdownNow();
    }
}
