package com.epam.concurrency.lesson2;

import java.io.IOException;

public class SimpleVolatile {

    // один котелок
    private static Boolean flag = true;

    public static void main(String[] args) {
        new ThreadFlagSetter().start();
        new ThreadFlagReader().start();
    }

    // котелок - со своим флагом = тру
    public static class ThreadFlagReader extends Thread {
        @Override
        public void run() {
            System.err.println("waiting...");
            while (SimpleVolatile.flag) {
                System.out.println();
            }
            System.err.println("Gogogo...");
        }
    }

    // котелок - со своим флагом = тру
    public static class ThreadFlagSetter extends Thread {
        @Override
        public void run() {
            try {
                int k = System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            flag = false;
            System.err.println("Flan now is down!");
        }
    }
}
