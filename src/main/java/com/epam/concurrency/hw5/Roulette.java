package com.epam.concurrency.hw5;

import java.util.Random;
import java.util.concurrent.*;

public class Roulette {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newCachedThreadPool();
        ConcurrentSkipListMap<Integer, GambleAddict> betMap = new ConcurrentSkipListMap<>();
        for (int i = 0; i < 5; i++) {
            GambleAddict addict = new GambleAddict((i + 1), latch);
            Future<Integer> bet = executorService.submit(addict);
            try {
                if (bet.isDone()) {
                    betMap.put(bet.get(), addict);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        try {
            latch.await();
            int win = new Random().nextInt(37);
            System.out.println("Winning number is " + win);
            if (betMap.containsKey(win)) {
                System.out.println("Winner is " + betMap.get(win));
            } else {
                System.out.println("Casino wins");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }
}
