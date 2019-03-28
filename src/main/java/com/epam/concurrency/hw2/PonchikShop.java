package com.epam.concurrency.hw2;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class PonchikShop {
    private AtomicLong lastTicket = new AtomicLong(1L);
    private Set<Long> activeTickets = new HashSet<>();
    private Object lock = new Object();

    public long getTicket() {
        Long ticket = null;
        synchronized (lock) {
            ticket = lastTicket.getAndIncrement();
            activeTickets.add(ticket);
        }
        return ticket;
    }

    public Ponchik sellPonchik(long ticket) {
        synchronized (lock) {
            if (!activeTickets.contains(ticket)) {
                System.err.println("It's fake ticket!");
                return null;
            } else {
                activeTickets.remove(ticket);
                return new Ponchik();
            }
        }
    }
}
