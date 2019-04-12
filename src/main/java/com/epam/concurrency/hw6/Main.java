package com.epam.concurrency.hw6;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Path path = Paths.get("/logs");
//        Report report = new ReportGenerator().createReport();
//        ReportExporter reportExporter = new ReportExporter(path, report, 20);
//
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 50; i++) {
//            executorService.submit(reportExporter);
//        }
        ReportImporter reportImporter = new ReportImporter(path, 1);
        reportImporter.importReport();

//        executorService.shutdown();
    }
}
