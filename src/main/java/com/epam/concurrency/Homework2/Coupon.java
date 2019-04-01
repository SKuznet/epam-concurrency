package com.epam.concurrency.Homework2;

import java.util.concurrent.atomic.AtomicInteger;

public class Coupon {

    private AtomicInteger number;

    public Coupon(AtomicInteger number) {
        this.number = number;
    }

    public AtomicInteger getNumber() {
        return number;
    }

    public void setNumber(AtomicInteger number) {
        this.number = number;
    }
}
