package com.epam.concurrency.hw2;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class PonchikEater implements Runnable {
    private static final PonchikShop shop = new PonchikShop();
    private Random random = ThreadLocalRandom.current();
    private long ticket;
    private Ponchik ponchik = null;

    @Override
    public void run() {
        ticket = shop.getTicket();
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
        ponchik = shop.sellPonchik(ticket);

        if (ponchik != null) {
            System.out.println("Ticket: " + ticket + " :: We good.");
        } else {
            System.err.println("Ticket: " + ticket + " :: No ponckik!");
            System.exit(1);
        }
    }
}
