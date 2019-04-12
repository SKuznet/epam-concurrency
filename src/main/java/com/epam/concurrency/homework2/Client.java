package com.epam.concurrency.homework2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.epam.concurrency.homework2.CouponMachine.donutMachine;

public class Client implements Callable<Coupon> {

    private int clientId;

    private static List<Integer> listOfHappyClient = new ArrayList<>();

    public Client(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public Coupon call() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Coupon coupon = CouponMachine.createCoupon();
                System.err.println("client: " + clientId +
                        " receive coupon: " + coupon.getNumber());
                Donut donut = donutMachine.getDonut(coupon);
                if (listOfHappyClient.contains(clientId)) {
                    System.err.println("Client " + clientId + " have Already received donut");
                    System.exit(1);
                } else {
                    listOfHappyClient.add(clientId);
                    System.err.println(("client: " + clientId +
                            " receive donut: " + donut.getDonutId()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.err.println("got interrupted");
        throw new InterruptedException("Were interrupted");
    }
}


