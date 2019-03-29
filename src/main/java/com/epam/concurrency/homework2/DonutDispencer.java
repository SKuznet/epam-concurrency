package com.epam.concurrency.homework2;

import java.util.concurrent.atomic.AtomicInteger;

public class DonutDispencer {

//    private AtomicInteger i = new AtomicInteger(0);
//
//    public int getDonutNumber(){
//        return i.incrementAndGet();
//    }

    private int i = 0;

    public int getDonutNumber() {
        return ++i;
    }
}
