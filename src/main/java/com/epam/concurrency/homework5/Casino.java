package com.epam.concurrency.homework5;

import java.util.Random;
import java.util.concurrent.*;

public class Casino {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newCachedThreadPool();
        ConcurrentSkipListMap<Integer, Player> betMap = new ConcurrentSkipListMap<>();
        for (int i = 0; i < 5; i++) {
            Player player = new Player((i + 1), latch);

            Future bet = executorService.submit(player);
            try {
                if (bet.isDone()) {
                    betMap.put((Integer) bet.get(), player);
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

class Player implements Callable<Integer> {

    private int bet;
    private int id;
    private CountDownLatch latch;

    public Player(int id, CountDownLatch latch) {
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

