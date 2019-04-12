package com.epam.concurrency.hw2;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Cafe {
    public static void main(String[] args) {
        CouponMachine couponMachine = new CouponMachine();
        Set<Integer> usedCoupons = new HashSet<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                while (true) {
                    int newCoupone = couponMachine.getCoupone();
                    if (!usedCoupons.add(newCoupone)) {
                        System.err.println("Coupon has been used!");
                        System.exit(1);
                    }
                    System.out.printf("Donut given for coupon %d\n", newCoupone);
                }
            });
        }

        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("You are late");
            System.exit(1);
        });
    }
}
