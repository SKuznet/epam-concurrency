package com.epam.concurrency.homework2;

import static com.epam.concurrency.homework2.CouponMachine.donutMachine;

public class Client implements Runnable {

    private int clientId;
    private CouponMachine couponMachine;
    private Coupon coupon;

    public Client(int clientId, CouponMachine couponMachine) {
        this.couponMachine = couponMachine;
        this.clientId = clientId;
    }

    @Override
    public void run() {
            try {
                coupon = couponMachine.createCoupon();
                System.err.println("client: " + clientId +
                        " get coupon: " + coupon.getNumber());

                Donut donut = donutMachine.getDonut(coupon);
                System.err.println(("client: " + clientId +
                        " get donut: " + donut.getDonutId()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}


