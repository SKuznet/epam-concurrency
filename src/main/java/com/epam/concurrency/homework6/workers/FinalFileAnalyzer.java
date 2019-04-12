package com.epam.concurrency.homework6.workers;

import java.io.*;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("all")
public class FinalFileAnalyzer implements Runnable {
    private static final ConcurrentHashMap<String, Map<String, Integer>> results = new ConcurrentHashMap<>();
    private static final File DIRECTORY = new File(DirectoriesCreator.getOutDirectoryName());
    private static final List<File> files = Collections.synchronizedList((Arrays.asList(DIRECTORY.listFiles())));
    private static AtomicInteger takenFilesCounter = new AtomicInteger(0);

    private CyclicBarrier cyclicBarrier;
    private int takenFile;

    public FinalFileAnalyzer(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            if (!files.isEmpty()) {
                readTempFiles();
            } else {
                return;
            }
            cyclicBarrier.await();
        } catch (IOException | InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void readTempFiles() throws IOException {
        takenFile = takenFilesCounter.getAndIncrement();
        File file = files.get(takenFile);
        Map<String, Integer> employeeUrls;
        Integer timeSpentOnUrl;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                final String[] split = currentLine.split(",");
                String login = split[0];
                String url = split[1];
                Integer time = Integer.valueOf(split[2]);

                if (!results.containsKey(login)) {
                    employeeUrls = new HashMap<>();
                    results.put(login, employeeUrls);
                }

                employeeUrls = results.get(login);
                if (!employeeUrls.containsKey(url)) {
                    timeSpentOnUrl = time;
                    employeeUrls.put(url, timeSpentOnUrl);
                } else {
                    Integer alreadySpentTime = employeeUrls.get(url);
                    employeeUrls.put(url, alreadySpentTime + time);
                }

                results.put(login, employeeUrls);
            }
        }
        file.delete();
        files.remove(file);
    }

    public static ConcurrentHashMap<String, Map<String, Integer>> getResults() {
        return results;
    }
}
