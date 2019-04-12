package com.epam.concurrency.hw2;

import java.util.concurrent.atomic.AtomicInteger;

public class CouponMachine {
    private AtomicInteger coupons = new AtomicInteger(0);

    public int getCoupone() {
        return coupons.getAndAdd(1);
    }
}
