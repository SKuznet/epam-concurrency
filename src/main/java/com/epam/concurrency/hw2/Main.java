package com.epam.concurrency.hw2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        TicketDispenser ticketDispenser = new TicketDispenser();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executorService.submit(ticketDispenser);
        }
    }
}
