package com.epam.concurrency.homework6.barriers;

import java.util.concurrent.CyclicBarrier;

public class ReadCyclicBarrier {
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
        System.err.println("Parsed 5 files");
    });

    public CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }
}
