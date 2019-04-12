package com.epam.concurrency.homework6;

import com.epam.concurrency.homework6.barriers.ReadCyclicBarrier;
import com.epam.concurrency.homework6.barriers.WriteCyclicBarrier;
import com.epam.concurrency.homework6.workers.DirectoriesCreator;
import com.epam.concurrency.homework6.workers.FileAnalyzer;
import com.epam.concurrency.homework6.workers.FileCreator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) {
        DirectoriesCreator.createDirectories();
        final ExecutorService executorService = Executors.newCachedThreadPool();

        final WriteCyclicBarrier writeCyclicBarrier = new WriteCyclicBarrier();
        for (int i = 0; i < 50; i++) {
            executorService.submit(new FileCreator(writeCyclicBarrier.getCyclicBarrier()));
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final ReadCyclicBarrier readCyclicBarrier = new ReadCyclicBarrier();
        for (int i = 0; i < 50; i++) {
            executorService.submit(new FileAnalyzer(readCyclicBarrier.getCyclicBarrier()));
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FileAnalyzer.setPassedTempPhase(true);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new FileAnalyzer(readCyclicBarrier.getCyclicBarrier()));
        }
        executorService.shutdown();
        System.err.println("Report is ready");
    }
}
