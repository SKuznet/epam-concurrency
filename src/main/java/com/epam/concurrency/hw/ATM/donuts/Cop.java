package com.epam.concurrency.hw.ATM.donuts;

import java.util.concurrent.TimeUnit;

public class Cop implements Runnable {

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            Integer coupon = CouponMachine.giveCoupon();
            System.err.println("cop from thread "
                    + Thread.currentThread().getName() + " get coupon " + coupon);
            if (CouponMachine.useCoupon(coupon).isPresent()) {
                System.err.println("cop from thread "
                        + Thread.currentThread().getName() + " eat donut!");
            } else {
                System.err.println("Code is already used!");
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                }
                System.exit(1);
            }
        }
    }
}
