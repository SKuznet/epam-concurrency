package com.epam.concurrency.homework6.workers;

import com.epam.concurrency.homework6.data.DataHolder;
import com.epam.concurrency.homework6.util.CSVUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

@SuppressWarnings("all")
public class FileAnalyzer implements Runnable {
    private static final BlockingQueue<String> fileNamesQueue = DataHolder.getFileNamesQueue();
    private static boolean passedTempPhase;

    private final CyclicBarrier cyclicBarrier;
    private final ConcurrentHashMap<String, Map<String, List<Integer>>> readData;
    private File inputFile;
    private String tempOutputFileName;
    private File tempOutputFile;

    public FileAnalyzer(CyclicBarrier cyclicBarrier) {
        try {
            this.inputFile = new File(fileNamesQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tempOutputFileName = inputFile.getName();
        this.tempOutputFile = new File(DirectoriesCreator.getOutDirectoryName() + File.separator + tempOutputFileName);
        this.cyclicBarrier = cyclicBarrier;
        this.readData = new ConcurrentHashMap<>();
    }

    @Override
    public void run() {
        try {
            if (!passedTempPhase) {
                readFile();
                writeFile();
                cyclicBarrier.await();
            } else {

            }
        } catch (IOException | InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void writeFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempOutputFile))) {
            tempOutputFile.createNewFile();
            Integer resultingTime = 0;
            final Enumeration<String> keys = readData.keys();
            while (keys.hasMoreElements()) {
                final String employee = keys.nextElement();
                final Map<String, List<Integer>> urls = readData.get(employee);
                for (String current : urls.keySet()) {
                    for (Integer time : urls.get(current)) {
                        resultingTime += time;
                    }
                    CSVUtils.writeLine(writer, Arrays.asList(employee, current, String.valueOf(resultingTime)));
                    resultingTime = 0;
                }
            }
            writer.flush();
        }
    }

    private void readFile() throws IOException {
        Map<String, List<Integer>> employeeUrls;
        List<Integer> timeRecords;
        String login = "";
        String url = "";
        Integer timeSpent;
        if (inputFile.exists() && inputFile.canRead() && inputFile.isFile() && inputFile.canWrite()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String current = reader.readLine();
                while ((current = reader.readLine()) != null) {
                    final String[] chunks = current.split(",");

                    login = chunks[0].replace("Login=", "");
                    url = chunks[1].replace("URL=", "");
                    timeSpent = Integer.valueOf(chunks[2].replace("Time=", ""));

                    if (!readData.containsKey(login)) {
                        employeeUrls = new HashMap<>();
                        readData.put(login, employeeUrls);
                    }

                    employeeUrls = readData.get(login);
                    if (!employeeUrls.containsKey(url)) {
                        timeRecords = new ArrayList<>();
                        employeeUrls.put(url, timeRecords);
                    }

                    timeRecords = employeeUrls.get(url);
                    timeRecords.add(timeSpent);
                }

                Integer result = 0;
                for (Map<String, List<Integer>> urls : readData.values()) {
                    for (List<Integer> time : urls.values()) {
                        result = time.stream().reduce((a, b) -> a + b).get();
                    }
                }
            }
        }
        inputFile.delete();
    }

    public static void setPassedTempPhase(boolean passedTempPhase) {
        FileAnalyzer.passedTempPhase = passedTempPhase;
    }
}
