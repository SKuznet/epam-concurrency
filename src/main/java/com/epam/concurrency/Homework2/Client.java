package com.epam.concurrency.Homework2;

import java.util.concurrent.Callable;

public class Client implements Runnable{

    private Coupon coupon;

    public Client(Coupon coupon) {
        this.coupon = coupon;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    @Override
    public void run() {
        System.out.println("client get coupon:" + coupon.getNumber());
    }
}
