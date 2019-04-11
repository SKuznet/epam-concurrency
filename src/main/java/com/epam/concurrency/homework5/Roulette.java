package com.epam.concurrency.homework5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Roulette {

    private CyclicBarrier cyclicBarrier;

    private List<Player> listOfPlayers = new ArrayList<>();

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private int winnerNumber;

    private boolean hasWinner;

    private int countOfGames;

    public Roulette(int numberOfPlayers, int pause) {
        countOfGames = 0;
        cyclicBarrier = new CyclicBarrier(numberOfPlayers, new Runnable() {
            @Override
            public void run() {

                for (Player player : listOfPlayers) {
                    System.err.println("Player " + player.getId() + " bet number " + player.bet());
                }

                Random random = new Random();
                winnerNumber = random.nextInt(37);
                System.err.println("Winner number is : " + winnerNumber);

                for (Player player: listOfPlayers) {
                    if(player.getBetNumber() == winnerNumber){
                        System.err.println("player " + player.getId() + " Won! ");
                        hasWinner = true;
                    }
                }

                if(!hasWinner) {
                    System.err.println("Casino win");
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                countOfGames++;
                if (countOfGames == 3) {
                    executorService.shutdownNow();
                    return;
                }
            }
        });

        for (int i = 0; i < 5; i++) {
            Player player = new Player(cyclicBarrier, i + 1);
            listOfPlayers.add(player);
            executorService.execute(player);
        }
    }
}
