package com.epam.concurrency.homework2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        CouponMachine machine = new CouponMachine();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        int newCoupon = machine.createCoupon();
                        if (machine.getCouponList().contains(newCoupon)) {
                            System.err.println("Error");
                            System.exit(0);
                        }
                        machine.addCoupon(newCoupon);
                    }
                }
            });
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println(machine.getCouponList().size());
                System.err.println("Turn off the machine");
                System.exit(0);
            }
        });
        executorService.shutdown();
    }

}
