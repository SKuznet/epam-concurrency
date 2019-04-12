package com.epam.concurrency.homework6.workers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FinalReportCreator implements Runnable {
    private static final ConcurrentHashMap<String, Map<String, Integer>> results = FinalFileAnalyzer.getResults();
    private static final String OUTPUT_FILE_NAME = DirectoriesCreator.getOutDirectoryName() + File.separator + "report.csv";

    @Override
    public void run() {
        File file = new File(OUTPUT_FILE_NAME);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            final Enumeration<String> keys = results.keys();
            while (keys.hasMoreElements()) {
                final String employee = keys.nextElement();
                final Map<String, Integer> urlTimeSummary = results.get(employee);
                for (String url : urlTimeSummary.keySet()) {
                    Integer spentTime = urlTimeSummary.get(url);
                    writer.append(employee)
                            .append(",")
                            .append(url)
                            .append(",")
                            .append(String.valueOf(spentTime))
                            .append(System.lineSeparator());
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
