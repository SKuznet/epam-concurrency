package com.epam.concurrency.homework2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class CouponHolder {

    private static AtomicInteger couponsCounter = new AtomicInteger(1);

    private static AtomicIntegerArray coupons = new AtomicIntegerArray(100);

    public static Integer giveNextCoupon() {
        synchronized (CouponHolder.class) {
            Integer currentCoupon = couponsCounter.get();
            checkCoupon(currentCoupon);
            coupons.set(currentCoupon, couponsCounter.getAndAdd(1));
            System.err.println("Giving next coupon: " + currentCoupon);
            return currentCoupon;
        }
    }

    private static void checkCoupon(Integer currentCoupon) {
        for (int i = 0; i < coupons.length(); i++) {
            if (currentCoupon.equals(coupons.get(i))) {
                System.err.println("Such coupon already exists: " + currentCoupon);
                System.exit(1111111111);
            }
        }
    }

}
