package com.epam.concurrency.Homework2;

import java.util.concurrent.Callable;

public class DonnutAutomat implements Callable<Coupon> {

    static private int count = 0;

    @Override
    public Coupon call() {
        count++;
        return new Coupon(count);
    }

}
