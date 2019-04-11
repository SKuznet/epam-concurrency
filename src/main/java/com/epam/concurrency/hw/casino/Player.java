package com.epam.concurrency.hw.casino;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Player implements Runnable {
    static int MAX_VALUE = 36;
    static final int NUMBER_OF_BETS = 5;
    int id;
    List<Bet> bets;
    int currentMoney = 50;
    CyclicBarrier cyclicBarrier;
    Player(int id, List<Bet> bets, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.bets = bets;
        this.cyclicBarrier = cyclicBarrier;
    }
    @Override
    public void run() {
        for (int i = 0; i < NUMBER_OF_BETS; i++) {
            placeBet();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private void placeBet() {
        int moneyBet = new Random().nextInt(currentMoney);
        int placeBet = new Random().nextInt(MAX_VALUE+1);
        bets.add(new Bet(id,placeBet,moneyBet));
        System.out.println("Player["+id+"] placed on " + placeBet + " money: " + moneyBet);
        currentMoney -= moneyBet;
    }
}
