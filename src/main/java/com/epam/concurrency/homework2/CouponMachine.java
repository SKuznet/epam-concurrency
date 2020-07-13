package com.epam.concurrency.homework2;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CouponMachine implements Callable<Integer> {

    private boolean isSluggish;

    @Override
    public Integer call() throws Exception {
        if (isSluggish) {
            SluggishCouponMachine.actWeird();
        }
        Integer coupon = CouponHolder.giveNextCoupon();
        System.err.println("Coupon " + coupon + " is given");
        return coupon;
    }

    public boolean isSluggish() {
        return isSluggish;
    }

    public void setSluggish(boolean sluggish) {
        isSluggish = sluggish;
    }


    private static class SluggishCouponMachine {

        private static void actWeird() throws InterruptedException {
            TimeUnit.SECONDS.sleep(10);
        }

    }

}
