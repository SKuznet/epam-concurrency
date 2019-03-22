package com.epam.concurrency.lesson1;

public class MultiThread {
    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            Thread thread = new Thread(new SpellCast(5));
            thread.start();
        }
        System.out.println("Waiting for spell cast end!");
    }
}
