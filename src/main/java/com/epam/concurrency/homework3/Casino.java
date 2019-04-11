package com.epam.concurrency.homework3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Casino {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Roulette(players, executorService));
        for (int i = 1; i < 6; i++) {
            Player player = new Player(i, cyclicBarrier);
            players.add(player);
            executorService.execute(player);
        }

    }
}

class Roulette implements Runnable {

    private final int TIMES_TO_SPIN;
    private Random random;
    private List<Player> players;
    private int spinCounter;
    private ExecutorService executorService;

    public Roulette(List<Player> players, ExecutorService executorService) {
        this.players = players;
        this.random = new Random();
        this.executorService = executorService;
        TIMES_TO_SPIN = 3;
        spinCounter = 0;
    }

    @Override
    public void run() {
        System.out.println("spinning the roulette");
        try {
            TimeUnit.MILLISECONDS.sleep(300);
            int winnerNumber = random.nextInt(37);
            System.out.println("winning number is " + winnerNumber);

            for (Player player : players) {
                if (player.getCurrentBet() == winnerNumber) {
                    System.out.println("player " + player.getId() + " won");
                    return;
                }
            }
            System.out.println("noone won");

        } catch (InterruptedException e) {
            System.err.println("roulette was broken");
        } finally {
            spinCounter++;
            if (spinCounter>=TIMES_TO_SPIN){
                executorService.shutdownNow();
            }
        }

    }
}

class Player implements Runnable {

    private int id;
    private Random random;
    private CyclicBarrier barrier;

    public int getCurrentBet() {
        return currentBet;
    }

    public int getId() {
        return id;
    }

    private int currentBet;

    public Player(int id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            currentBet = random.nextInt(37);
            System.out.println("player " + id + " made a bet on " + currentBet);
            try {
                barrier.await();
            } catch (InterruptedException e) {
                System.err.println("couldn't finish waiting");
            } catch (BrokenBarrierException e) {
                System.err.println("roulette was broken");
            }
        }
    }
}