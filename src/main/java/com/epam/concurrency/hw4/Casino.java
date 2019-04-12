package com.epam.concurrency.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Casino {
    private List<Player> players = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private final int SPIN_NUMBER = 3;
    private int spinCounter;

    public Casino(int playersNumber, int pause) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(playersNumber, () -> {

            System.err.println("Place your bets");
            for (Player player : players) {
                System.out.println(player.toString() + " bet on " + player.getBet() + " " + "1000$");
            }

            try {
                TimeUnit.MILLISECONDS.sleep(pause);
                int winNumber = (int) (Math.random() * 37);
                System.err.println("\nWinning number is " + winNumber);

                for (Player player : players) {
                    if (player.getBet() == winNumber) {
                        System.out.println(player.toString() + " won");
                        return;
                    }
                }
                TimeUnit.MILLISECONDS.sleep(pause);
                System.out.println("Casino won. Continue, if you want to loose all your money.\n");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (spinCounter++ >= SPIN_NUMBER) {
                    System.out.println("It is looks like you have no money. Goodbye.");
                    executorService.shutdownNow();
                }
            }

        });
        Thread.currentThread().checkAccess();

        for (int i = 0; i < playersNumber ; i++) {
            Player player = new Player(cyclicBarrier);
            players.add(player);
            executorService.execute(player);
        }
    }

    public static void main(String[] args) {
        int playersNumber = 5;
        int pause = 500;
        System.err.println("Welcome to lose-lose Casino");
        new Casino(playersNumber, pause);
    }
}

class Player implements Runnable {
    private static int counter;
    private static CyclicBarrier cyclicBarrier;
    private static Random random = new Random(47);
    private final int id = counter++;
    private int bet;

    public Player(CyclicBarrier barrier) {
        cyclicBarrier = barrier;
    }

    public synchronized int getBet() {
        return bet;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    bet = random.nextInt(37);
                }
                cyclicBarrier.await();
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Player " + id + " ";
    }
}


