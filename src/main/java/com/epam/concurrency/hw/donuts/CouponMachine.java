package com.epam.concurrency.hw.donuts;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class CouponMachine {
    public static AtomicInteger currentCoupon = new AtomicInteger(0);
    public static Set<Integer> usedCoupons =  new HashSet<>();

    static Integer giveCoupon() {
        return currentCoupon.incrementAndGet();
    }

    static public Optional<Donut> useCoupon(Integer givenCoupon) {
        System.err.println("Try to use coupon " + givenCoupon);
        if (usedCoupons.contains(givenCoupon)) {
            return Optional.empty();
        } else {
            usedCoupons.add(givenCoupon);
            return Optional.of(new Donut());
        }
    }
}
