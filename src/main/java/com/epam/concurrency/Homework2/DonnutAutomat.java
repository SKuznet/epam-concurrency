package com.epam.concurrency.Homework2;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;


public class DonnutAutomat implements Callable<Coupon> {

    static private AtomicInteger count = new AtomicInteger(0);
    private int automatId;

    public DonnutAutomat(int automatId) {
        this.automatId = automatId;
    }

    @Override
    public Coupon call() throws InterruptedException {
            count.getAndAdd(1);
            return new Coupon(count);
    }

    public int getAutomatId() {
        return automatId;
    }

    public void setAutomatId(int automatId) {
        this.automatId = automatId;
    }
}
