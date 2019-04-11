package com.epam.concurrency.hw2;

import java.util.concurrent.atomic.AtomicInteger;

public class CouponMachine {
    private AtomicInteger couponNumber = new AtomicInteger(0);


    public int getCoupon() {
        System.out.println("Coupon #" + couponNumber.get() +1);
        return couponNumber.getAndAdd(1);
    }


}
