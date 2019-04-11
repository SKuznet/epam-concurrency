package com.epam.concurrency.lesson6;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new UserString(exchanger);
        new MakerString(exchanger);
    }
}

class MakerString implements Runnable {
    private Exchanger<String> exchanger;
    private String string;

    public MakerString(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
        string = "";
        new Thread(this).start();
    }

    @Override
    public void run() {
        char ch = 'A';

        // fill buffer
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                string += (char) ch++;
            }
            try {
                string = exchanger.exchange(string);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

class UserString implements Runnable {
    private Exchanger<String> exchanger;
    private String string;

    public UserString(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            // exchange empty buffer for filled

            try {
                string = exchanger.exchange(new String());
                System.out.println("Got: " + string);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
