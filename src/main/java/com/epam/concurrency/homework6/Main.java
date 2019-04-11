package com.epam.concurrency.homework6;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        CyclicBarrier barrier = new CyclicBarrier(51);
        CsvFileReader fileReader = new CsvFileReader();
        int cnt = 0;
        try {
            for (int i = 0; i < 10; i++) {
                for (int i1 = 0; i1 < 5; i1++) {
                    Path path = Paths.get("C:\\temp\\in\\file" + cnt++ + ".cvs");
                    service.execute(new FileGenerator(path, barrier));
                }
            }
            barrier.await();
            System.out.println("Waiting all files...");
            cnt = 0;
            for (int i = 0; i < 10; i++) {
                for (int i1 = 0; i1 < 5; i1++) {
                    Path path = Paths.get("C:\\temp\\in\\file" + cnt++ + ".cvs");
                    fileReader.readCsvFile(path.toString());
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        service.shutdownNow();
    }
}

