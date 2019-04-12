package com.epam.concurrency.hw6;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        Path path = Paths.get("/logs");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<?> submit = null;
        for (int i = 0; i < 50; i++) {
            submit = executorService.submit(new ReportExporter(path, 20));
        }
        try {
            submit.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        ReportImporter reportImporter = new ReportImporter(path, 1);
        reportImporter.importReport();

        executorService.shutdown();
    }
}
