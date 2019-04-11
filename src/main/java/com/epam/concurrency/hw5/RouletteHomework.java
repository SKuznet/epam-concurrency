package com.epam.concurrency.hw5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RouletteHomework implements Runnable {
    private static final int NUMBER_OF_PLAYERS = 5;
    private static final int MAX_NUMBER_OF_GAMES = 3;
    private static final int MAX_BET = 37;
    private final List<Player> players;
    private final int maxBet;
    private Random random = new Random();
    private int numberOfGames = 0;
    private ExecutorService executorService;

    public RouletteHomework(List<Player> players, ExecutorService executorService, int maxBet) {
        this.players = players;
        this.executorService = executorService;
        this.maxBet = maxBet;
    }

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();

        CyclicBarrier barrier = new CyclicBarrier(NUMBER_OF_PLAYERS, new RouletteHomework(players, executorService, MAX_BET));
        for (int i = 1; i < NUMBER_OF_PLAYERS + 1; i++) {
            Player player = new Player(i, barrier, MAX_BET);
            players.add(player);
            executorService.execute(player);
        }
    }

    @Override
    public void run() {
        boolean anyoneWon = false;
        int currentWinNumber = random.nextInt(maxBet) + 1;
        System.err.println("Current win number: " + currentWinNumber);

        for (Player player : players) {
            if (player.getBet() == currentWinNumber) {
                System.err.println("Player " + player.getId() + " won!");
                anyoneWon = true;
            }
        }

        if (!anyoneWon) {
            System.err.println("Nobody won...");
        }

        numberOfGames++;
        System.out.println();

        if (numberOfGames >= MAX_NUMBER_OF_GAMES) {
            executorService.shutdownNow();
        }
    }
}

class Player implements Runnable {
    private final CyclicBarrier barrier;
    private final int maxBet;
    private int id;
    private Random random = new Random();
    private int bet;

    public Player(int id, CyclicBarrier barrier, int maxBet) {
        this.id = id;
        this.barrier = barrier;
        this.maxBet = maxBet;
    }

    public int getId() {
        return id;
    }

    public int getBet() {
        return bet;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            this.bet = random.nextInt(maxBet) + 1;
            System.out.println("Player " + id + " bet on " + bet + "!");
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
