package com.epam.concurrency.homework2;

import java.util.ArrayList;

public class CouponMachine {

    private ArrayList<Integer> couponList;
    private volatile int c = 0;

    public CouponMachine() {
        this.couponList = new ArrayList();
    }

    public ArrayList<Integer> getCouponList() {
        return couponList;
    }

    synchronized int createCoupon() {
        c++;
        return c;
    }

    synchronized void addCoupon(int coupon) {
        couponList.add(coupon);
    }

}
