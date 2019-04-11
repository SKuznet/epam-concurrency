package com.epam.concurrency.lesson7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CyclicBarrierExample {
    private static final int FINISH_LINE = 20;
    private List<Horse> horses = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierExample(int nHorses, int pause) {
       cyclicBarrier = new CyclicBarrier(nHorses, new Runnable() {
           @Override
           public void run() {
               StringBuilder stringBuilder = new StringBuilder();

               for (int i = 0; i < FINISH_LINE; i++) {
                   stringBuilder.append("=");
               }
               System.out.println(stringBuilder);

               for (Horse horse: horses) {
                   System.out.println(horse.tracks());
               }

               for (Horse horse: horses) {
                   if(horse.getStrides() >= FINISH_LINE) {
                       System.out.println(horse + "won!");
                       executorService.shutdownNow();
                       return;
                   }
               }

               try {
                   TimeUnit.MILLISECONDS.sleep(pause);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       });
        Thread.currentThread().suspend();

        for (int i = 0; i < nHorses ; i++) {
            Horse horse = new Horse(cyclicBarrier);
            horses.add(horse);
            executorService.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorses = 7;
        int pause = 200;
        new CyclicBarrierExample(nHorses, pause);
    }
}

class Horse implements Runnable {
    private static int counter;
    private static CyclicBarrier cyclicBarrier;
    private static Random random = new Random(47);
    private final int id = counter++;
    private int strides;

    public Horse(CyclicBarrier barrier) {
        cyclicBarrier = barrier;
    }

    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += random.nextInt(3); // 0 1 2
                }
                cyclicBarrier.await();
            }
        } catch (InterruptedException e) {
            // it's ok
        } catch (BrokenBarrierException e) {
            // not ok
        }
    }

    @Override
    public String toString() {
        return "Horse " + id + " ";
    }

    public String tracks() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            builder.append("*");
        }
        builder.append(id);
        return builder.toString();
    }
}
