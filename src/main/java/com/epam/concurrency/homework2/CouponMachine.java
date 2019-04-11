package com.epam.concurrency.homework2;

public class CouponMachine {

    static DonutMachine donutMachine = new DonutMachine();

    public static Coupon createCoupon() throws InterruptedException {
        int couponNumber = CouponCreatingService.getCouponNumber();
        Coupon coupon = new Coupon(couponNumber);
        donutMachine.createDonut(coupon);
        return coupon;
    }
}
