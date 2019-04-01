package com.epam.concurrency.Homework2;

public class CouponCreatingService {

    private static int lastNumber = 0;

    public static int getCouponNumber() {
        synchronized (CouponCreatingService.class) {
            return lastNumber++;
        }
    }
}
