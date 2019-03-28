package com.epam.concurrency.homework2;

import java.util.concurrent.Callable;

public class CouponMachine implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Integer coupon = CouponHolder.giveNextCoupon();
        System.err.println("Coupon " + coupon + " is given");
        return coupon;
    }

}
