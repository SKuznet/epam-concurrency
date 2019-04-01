package com.epam.concurrency.hw2;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class TicketDispenser implements Callable<Integer> {
    private static AtomicIntegerArray tickets = new AtomicIntegerArray(1000);
    private static AtomicInteger count = new AtomicInteger(1);

    @Override
    public Integer call() throws Exception {
        Integer nextTicket = getNextTicket();
        System.err.println("Ticket #" + nextTicket + " is given");
        return nextTicket;
    }

    public synchronized Integer getNextTicket() {
        Integer currentTicket = count.get();
        checkTicket(currentTicket);
        tickets.set(currentTicket, count.addAndGet(1));
        return currentTicket;
    }

    private static void checkTicket(Integer currentTicket) {
        for (int i = 0; i < tickets.length(); i++) {
            if (currentTicket.equals(tickets.get(i))) {
                System.err.println("This ticket is exists in tickets.");
                System.exit(0);
            }
        }
    }
}
