package com.epam.concurrency.hw.casino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    static int NUMBER_OF_PLAYERS = 3;
    static CyclicBarrier cyclicBarrier;
    static List<Bet> bets = Collections.synchronizedList(new ArrayList<>());
    static List<Player> players = new ArrayList<>();


    public static void main(String[] args) throws InterruptedException {


        cyclicBarrier = new CyclicBarrier(NUMBER_OF_PLAYERS, () -> {

            int sumBets = sumBets();
            System.err.println("Sum of bets : " + sumBets);
            System.err.println("Roulette is rolling!");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int placeRoulette = new Random().nextInt(37);
            System.err.println("Rolls on " + placeRoulette);
            List<Integer> winners = new ArrayList<>();
            for (Bet bet : bets) {
                if (bet.place == placeRoulette) {
                    winners.add(bet.player_id);
                }
            }

            if (winners.isEmpty()) {
                System.err.println("No one wins");
            } else {
                for (Integer winner : winners) {
                    System.err.println("Player with id " + winner + " wins!");
                    int win = sumBets / winners.size();
                    players.get(winner).currentMoney += win;
                    System.err.println("and he gets " + win);
                }
            }

            bets.clear();
        });
        for (int i = 0; i < NUMBER_OF_PLAYERS; ++i) {
            players.add(new Player(i,
                    bets,
                    cyclicBarrier));
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (Player player : players) {
            executorService.submit(player);
        }
        executorService.shutdown();
        executorService.awaitTermination(1,TimeUnit.HOURS);
        System.err.println("Final result : ");
        for (Player player : players) {
            System.err.println("Player[" + player.id +"] have "+ player.currentMoney);
        }
    }

    private static int sumBets() {
        int sum = 0;
        for (Bet bet : bets) {
            sum += bet.bet;
        }
        return sum;
    }
}
