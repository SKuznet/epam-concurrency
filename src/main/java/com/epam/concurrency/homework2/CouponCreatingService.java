package com.epam.concurrency.homework2;

import java.util.concurrent.atomic.AtomicInteger;

public class CouponCreatingService {

    private static AtomicInteger lastNumber = new AtomicInteger(0);

    public synchronized static int getCouponNumber() {
        return lastNumber.getAndAdd(1);
    }

}
