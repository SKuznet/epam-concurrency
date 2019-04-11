package com.epam.concurrency.homework2;

public class CouponMachine {

    private int machineId;
    static DonutMachine donutMachine = new DonutMachine();

    public CouponMachine(int machineId) {
        this.machineId = machineId;
    }

    public Coupon createCoupon() throws InterruptedException {
        int couponNumber = CouponCreatingService.getCouponNumber();
        Coupon coupon = new Coupon(couponNumber);
        donutMachine.createDonut(coupon);
        return coupon;
    }
}
