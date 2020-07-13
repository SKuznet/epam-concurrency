package com.epam.concurrency.homework4;

import java.util.concurrent.Exchanger;

public class Main {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        Taker taker = new Taker(exchanger);
        Giver giver = new Giver(exchanger);
        new Thread(giver).start();
        new Thread(taker).start();
        new Thread(taker).start();
    }
}
