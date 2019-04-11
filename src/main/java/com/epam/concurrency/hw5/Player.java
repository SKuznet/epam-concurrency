package com.epam.concurrency.hw5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ThreadLocalRandom;

public class Player implements Runnable{

    int playerId;
    private Rullete rullete;

    public Player(int playerId, Rullete rullete) {
        this.playerId = playerId;
        this.rullete = rullete;
    }

    public int getPlayerId() {
        return playerId;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Bet bet = null;
            try {
                Thread.sleep((random.nextInt(3) + 1) * 1000);
                bet = new Bet(playerId, random.nextInt(36), random.nextInt(5000));
                rullete.bet(bet);
            } catch (BrokenBarrierException | InterruptedException e) {
                System.err.println("Casino is closed im going home");
                if (bet != null) {
                    System.err.println("Getting money back.");
                }
                break;
            }
        }
    }
}
