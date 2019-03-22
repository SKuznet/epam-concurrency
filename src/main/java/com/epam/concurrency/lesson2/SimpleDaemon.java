package com.epam.concurrency.lesson2;

import java.util.concurrent.TimeUnit;

public class SimpleDaemon implements Runnable { //фоновый процесс для опрашивания чего нибудь
    @Override
    public void run() {
        while(true){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            } catch (InterruptedException e){
                System.err.println("sleep() interrupted ");
            }

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemon());
            daemon.setDaemon(true);
            daemon.start();
        }

        System.out.println("All daemons ran");
        try {
            TimeUnit.MILLISECONDS.sleep(175);//експериментируем с таймаутом
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
