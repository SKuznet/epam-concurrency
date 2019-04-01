package com.epam.concurrency.Homework2;

import java.util.concurrent.TimeUnit;

public class Client implements Runnable{

    private int clientId;

    private DonnutAutomat donnutAutomat;

    public Client(int clientId, DonnutAutomat donnutAutomat) {
        this.donnutAutomat = donnutAutomat;
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public void run() {

            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Coupon coupon = null;
                try {
                    coupon = donnutAutomat.call();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("client: " + clientId + " get coupon: " + coupon.getNumber() + " from donnutMachine: " + donnutAutomat.getAutomatId());
            }
        }

    }

