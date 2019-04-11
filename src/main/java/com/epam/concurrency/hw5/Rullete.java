package com.epam.concurrency.hw5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Rullete implements Runnable {

    private CyclicBarrier queing;
    private List<Bet> betList;
    private Object isGambling = new Object();

    public Rullete(int players) {
        this.queing = new CyclicBarrier(players + 1);
        betList = new ArrayList<>();
    }

    public void bet(Bet bet) throws BrokenBarrierException, InterruptedException {
        betList.add(bet);
        System.out.printf("Player %s bet %d on %d. \n", bet.getPlayerId(), bet.getDineros(), bet.getNumber());
        queing.await();
        synchronized (isGambling) {
            isGambling.wait();
        }

    }

    private void startNewRound() throws BrokenBarrierException, InterruptedException {
        System.out.println("\n New Round");
        queing.await();
        System.out.println("No more stakes!");
        int result = -1;
        for (int i = 10; i < 25; i++) {
            result = new Random().nextInt(37);
            Thread.sleep(i * i * 2);
            System.out.printf("%d ", result);
        }
        System.out.println("\nNumber: " + result + " has won");
        System.out.println("Winners are: ");
        int count = 0;
        for (Bet playerBet : betList) {
            if (playerBet.getNumber() == result) {
                System.out.printf("Player %d won %d \n", playerBet.getPlayerId(), playerBet.getDineros() * 37);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No one.");
        }
        betList.clear();
        synchronized (isGambling) {
            isGambling.notifyAll();
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                startNewRound();
            } catch (BrokenBarrierException e) {
                System.err.println("bad situation");
                break;
            } catch (InterruptedException e) {
                System.err.println("The game is over");
                break;
            }

        }
    }
}
