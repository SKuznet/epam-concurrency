package com.epam.concurrency.hw.casino;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Player implements Runnable {
    static final int NUMBER_OF_BETS = 3;
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

        }
    }
}
