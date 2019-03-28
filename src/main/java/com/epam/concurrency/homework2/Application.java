package com.epam.concurrency.homework2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        CouponMachine couponMachine = new CouponMachine();
        SluggishCouponMachine sluggishCouponMachine = new SluggishCouponMachine();
        ExecutorService executorService = Executors.newFixedThreadPool(11);

        for (int i = 0; i < 10; i++) {
           executorService.submit(couponMachine);
        }

        TimeUnit.SECONDS.sleep(1);
        System.err.println("We are fine.");

        System.err.println("That's a weird looking machine... Let's try to get a donut coupon from it.");
        Future<Integer> result = executorService.submit(sluggishCouponMachine);
        executorService.shutdown();

        TimeUnit.SECONDS.sleep(5);
        if (!result.isDone()) {
            System.err.println("Sluggish coupon machine detected! Get down, System.exit() is coming!");
            System.exit(1111111111);
        }
    }

}
