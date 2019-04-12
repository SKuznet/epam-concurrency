package com.epam.concurrency.homework.task2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Dispenser implements Runnable{

    private static AtomicIntegerArray ticketsArray = new AtomicIntegerArray(500);
    private static AtomicInteger currentTicketNum = new AtomicInteger(0);

    @Override
    public void run() {
        Integer ticketNum = getTicket();
        System.err.println("#" + ticketNum);
    }

    public synchronized Integer getTicket() {
        return currentTicketNum.addAndGet(1);
    }

    public static void chechTicketsArray(Integer ticketNumber) {
        for (int i = 0; i < ticketsArray.length(); i++) {
            if(ticketNumber.equals(ticketsArray.get(i))) {
                System.err.println("finita la comedia");
                System.exit(0);
            }
        }
    }
}
