package com.epam.concurrency.lesson2;

public class Sleeper extends Thread {
    private int duration;

    @Override
    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.err.println(getName() + " interrupted isInterrupted() " +  interrupted());
            return;
        }
        System.out.println(getName() + " activated");
    }

    public Sleeper(String name, int sleepTime) {
        super(name);
        this.duration = sleepTime;
        start();
    }
}
