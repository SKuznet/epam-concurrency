package com.epam.concurrency.lesson1;

public class SpellCast implements Runnable {
    private static int taskCount = 0;
    private final int id = taskCount++;
    protected int countDown = 10;

    public SpellCast() {
    }

    public SpellCast(int countDown) {
        this.countDown = countDown;
    }

    public String getStatus() {
        return "#" + id + ("(" + (countDown > 0 ? countDown : "Spell is end!") + ")");
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(getStatus());
            Thread.yield();
        }
    }
}
