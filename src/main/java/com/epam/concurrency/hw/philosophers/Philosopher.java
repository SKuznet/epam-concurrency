package com.epam.concurrency.hw.philosophers;

public class Philosopher implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; ++i) {
            Dish.eat(i);
        }
        System.err.println(Thread.currentThread().getName() + " finished");
    }
}
