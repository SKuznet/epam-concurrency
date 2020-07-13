package com.epam.concurrency.homework6.barriers;

import java.util.concurrent.CyclicBarrier;

public class WriteCyclicBarrier {
    private final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
        System.err.println("Wrote 10 files");
    });

    public CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }
}
