package com.epam.concurrency.lesson5.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeArrayList<E> {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final List<E> list = new ArrayList<>();

    public static void main(String[] args) {
        ThreadSafeArrayList<Integer> safeArrayList = new ThreadSafeArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            final int digit = i;
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    safeArrayList.set(digit);

                                    try {
                                        TimeUnit.MILLISECONDS.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            thread.start();
                        }
                    }
                }).start();


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int j = 0; j < 10; j++) {
                            try {
                                TimeUnit.MILLISECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.err.println("I am trying to read element with index 1: " + safeArrayList.get(1));
                        }
                    }
                }).start();


            }
        }).start();

        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.err.println(safeArrayList.get(i));
        }
    }

    public void set(E object) {
        writeLock.lock();

        try {
            list.add(object);
            System.err.println("Adding element " + object + " by thread " + Thread.currentThread().getName());
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int i) {
        readLock.lock();

        try {
            System.err.println("Getting element with index " + i + " by thread " + Thread.currentThread().getName());
            return list.get(i);
        } finally {
            readLock.unlock();
        }
    }
}
