package com.epam.concurrency.homework6.workers;

import com.epam.concurrency.homework6.data.DataHolder;
import com.epam.concurrency.homework6.util.CSVUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("all")
public class FileCreator implements Runnable {
    private static final String SEPARATOR = File.separator;
    private static final String IN_DIRECTORY_NAME = DirectoriesCreator.getInDirectoryName();
    private static final String FILE_EXTENSION = ".csv";
    private static final int ROWS_QUANTITY = 20;
    private static final int FILE_COUNT_THRESHOLD = 50;
    private static final AtomicInteger FILE_COUNTER = new AtomicInteger(0);

    private final String fileName;
    private final CyclicBarrier barrier;

    public FileCreator(CyclicBarrier cyclicBarrier) {
        this.fileName = IN_DIRECTORY_NAME + SEPARATOR + "file_" + FILE_COUNTER.getAndIncrement() + FILE_EXTENSION;
        this.barrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            DataHolder.getFileNamesQueue().put(fileName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadLocalRandom random = ThreadLocalRandom.current();
        try {
            createFile();
            fillFile();
            barrier.await();
        } catch (IOException | InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void createFile() throws IOException {
        final File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        file.setWritable(true);
    }

    private void fillFile() throws IOException {
        try (final FileWriter writer = new FileWriter(fileName)) {
            List<String> data = Arrays.asList("Employee", "URL", "Time spent");
            CSVUtils.writeLine(writer, data);
            for (int i = 0; i < ROWS_QUANTITY + 1; i++) {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                int randomEmployee = random.nextInt(0, 5);
                int randomUrl = random.nextInt(0, 5);
                int randomTimeSpent = random.nextInt(1, 601);
                data = Arrays.asList(
                        "Login=" + DataHolder.getEmployees().get(randomEmployee).getLogin(),
                        "URL=" + DataHolder.getUrls().get(randomUrl).getUrl(),
                        "Time=" + String.valueOf(randomTimeSpent));
                CSVUtils.writeLine(writer, data);
            }
            writer.flush();
        }
    }
}
