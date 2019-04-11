package com.epam.concurrency.hw2;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FreeDonutsCafe {

    public static void main(String[] args) {
        CouponMachine couponMachine = new CouponMachine();
        Set<Integer> freeDonuts = new HashSet<>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                while (true) {
                    int newCoupon = couponMachine.getCoupon();
                    if (!freeDonuts.add(newCoupon)) {
                        System.err.printf("Coupon #%d is not valid\n", newCoupon);
                        System.exit(1);
                    }
                }
            });
        }

        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.err.println("Free-Donuts cafe is closed. We have donated " + freeDonuts.size() + " donuts");
            System.exit(1);
        });
        executorService.shutdown();
    }
}
