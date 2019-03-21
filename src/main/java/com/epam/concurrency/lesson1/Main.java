package com.epam.concurrency.lesson1;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new SpellCast(5));
        thread.start();
        System.err.println("Waiting of spell cast end!");
    }
}
