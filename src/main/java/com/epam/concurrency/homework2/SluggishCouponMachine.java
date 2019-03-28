package com.epam.concurrency.homework2;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SluggishCouponMachine implements Callable<Integer> {

    @Override
    public Integer call() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        Integer coupon = CouponHolder.giveNextCoupon();
        System.err.println("Coupon " + coupon + " is given");
        return coupon;
    }

}
