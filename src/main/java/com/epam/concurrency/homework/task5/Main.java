package com.epam.concurrency.homework.task5;

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newCachedThreadPool();
        ConcurrentSkipListMap<Integer, Gambler> map = new ConcurrentSkipListMap<>();

        for (int i = 1; i < 6; i++) {
            Gambler gambler = new Gambler(countDownLatch, i);
            Future<Integer> gamblerBet = executorService.submit(gambler);
            try {
                if (gamblerBet.isDone()) {
                    map.put(gamblerBet.get(), gambler);
                }
            } catch (InterruptedException | ExecutionException exception) {
                exception.printStackTrace();
            }
        }

        try {
            countDownLatch.await();
            int winNum = new Random().nextInt(37);
            System.out.println("winning number: " + winNum);
            if (map.containsKey(winNum)) {
                System.out.println("winner is gambler #" + map.get(winNum).getId());
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        executorService.shutdownNow();
    }
}
