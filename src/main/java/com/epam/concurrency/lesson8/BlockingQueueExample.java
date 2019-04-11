package com.epam.concurrency.lesson8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueExample {

    private final String[] messages = {
            "You preparing to competency center!",
            "You passed competency!",
            "You received new grade!",
            "What next???"
    };
    private BlockingQueue<String> drop;
    public BlockingQueueExample() {
        drop = new ArrayBlockingQueue<>(1, true);
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }

    public static void main(String[] args) {
        new BlockingQueueExample();
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            try {
                int cnt = 0;
                for (String message : messages) {
                    drop.put(message);
                    if (++cnt < 3) {
                        TimeUnit.SECONDS.sleep(2);
                    }
                }

                drop.put("done!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                String msg;
                while (!(msg = drop.take()).equals("done!")) {
                    System.out.println(msg);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

