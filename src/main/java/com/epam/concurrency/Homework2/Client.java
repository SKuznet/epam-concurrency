package com.epam.concurrency.Homework2;

import java.util.concurrent.TimeUnit;

public class Client implements Runnable {

    private int clientId;
    private DonutMachine donutMachine;
    private Coupon coupon;

    public Client(int clientId, DonutMachine donutMachine) {
        this.donutMachine = donutMachine;
        this.clientId = clientId;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                coupon = donutMachine.call();
                System.out.println("client: " + clientId +
                        " get coupon: " + coupon.getNumber() +
                        " from donnutMachine: " + donutMachine.getMachineId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

