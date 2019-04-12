package com.epam.concurrency.lesson9;

import java.util.concurrent.TimeUnit;

public class DeadLockExample implements Runnable {
    private Consumer consumer = new Consumer();
    private Producer producer = new Producer();

    public DeadLockExample() {
        Thread.currentThread().setName("Main thread!");

        Thread thread = new Thread(this, "Versus thread!");
        thread.start();

        consumer.buySomething(producer);
        System.out.println("Go back to main thread!");
    }

    public static void main(String[] args) {
        new DeadLockExample();
    }

    @Override
    public void run() {
        producer.tryToProduceSomething(consumer);
        System.out.println("Back to another thread!");
    }
}

class Consumer {
    synchronized void buySomething(Producer producer) {
        String name = Thread.currentThread().getName();

        System.out.println(name + " enter into method Comsumer.buySomething()");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.err.println("Consumer interrupted");
        }

        System.out.println(name + " trying to invoke Producer.last()");
        producer.last();
    }

    synchronized void last() {
        System.out.println("Into method Consumer.last()");
    }
}

class Producer {
    synchronized void tryToProduceSomething(Consumer consumer) {
        String name = Thread.currentThread().getName();

        System.out.println(name + " enter into method Producer.tryToProduceSomething()");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.err.println("Producer interrupted");
        }

        System.out.println(name + " trying to invoke method Consumer.last()");
        consumer.last();
    }

    synchronized void last() {
        System.out.println("Into method Producer.last()");
    }
}
