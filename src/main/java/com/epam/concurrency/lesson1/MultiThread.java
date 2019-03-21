package com.epam.concurrency.lesson1;

public class MultiThread {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new SpellCast());
            thread.start();
        }
        System.out.println("Waiting end of spell casting...");
    }
}
