package com.epam.concurrency.Homework2;

import java.util.concurrent.Callable;


public class DonutMachine implements Callable<Coupon> {

    private int machineId;

    public DonutMachine(int machineId) {
        this.machineId = machineId;
    }

    @Override
    public Coupon call() throws InterruptedException {
        return new Coupon(CouponCreatingService.getCouponNumber());
    }

    public int getMachineId() {
        return machineId;
    }

}
