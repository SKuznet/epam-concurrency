package com.epam.concurrency.hw.casino;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class Main {
    static int NUMBER_OF_PLAYERS = 3;
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_PLAYERS);
    static List<Bet> bets;
    static int MIN_VALUE = 0;
    static int MAX_VALUE = 36;

    public static void main(String[] args) {

    }
}
