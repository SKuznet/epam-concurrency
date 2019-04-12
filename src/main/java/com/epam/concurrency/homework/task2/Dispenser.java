package com.epam.concurrency.homework.task2;

import java.util.concurrent.atomic.AtomicInteger;

public class Dispenser implements Runnable {

    private static AtomicInteger currentTicketNum = new AtomicInteger(0);

    @Override
    public void run() {
        Integer ticketNum = getTicket();
        System.err.println("#" + ticketNum);
    }

    public synchronized Integer getTicket() {
        return currentTicketNum.addAndGet(1);
    }
}
