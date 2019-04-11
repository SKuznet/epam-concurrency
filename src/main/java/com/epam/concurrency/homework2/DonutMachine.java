package com.epam.concurrency.homework2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DonutMachine {

    private volatile Map<Coupon, Donut> mapOfDonut = new HashMap<>();

    public synchronized Donut getDonut(Coupon coupon) {
        return mapOfDonut.get(coupon);
    }

    public synchronized void createDonut(Coupon coupon) throws InterruptedException {
        System.err.println("Donut " + coupon.getNumber() + " making time...");
        TimeUnit.MILLISECONDS.sleep(1);
        mapOfDonut.put(coupon, new Donut(coupon.getNumber()));
    }
}
