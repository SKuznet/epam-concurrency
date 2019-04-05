package com.epam.concurrency.hw3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore ducks = new Semaphore(5);
        ExecutorService hunters = Executors.newCachedThreadPool();
        for (int i = 1; i < 4; i++) {
            hunters.submit(new Hunter("hunter " + i, ducks, 3));
        }
        hunters.shutdown();
    }

    static class Hunter implements Runnable{

        private String name;
        private Semaphore ducks;
        private int duckCount;

        public Hunter(String name, Semaphore ducks, int duckCount) {
            this.name = name;
            this.ducks = ducks;
            this.duckCount = duckCount;
        }

        @Override
        public void run() {
            while (duckCount > 0) {
                try {
                    ducks.acquire();
                    System.out.println("hunter " + name + " shut the duck. " + duckCount + " to go.");
                    duckCount--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    ducks.release();
                }
            }
            System.out.println("hunter " + name + " got all ducks.");
        }
    }
}
