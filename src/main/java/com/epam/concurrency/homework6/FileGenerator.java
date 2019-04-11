package com.epam.concurrency.homework6;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class FileGenerator implements Runnable {
    private static String[] login = {"vasua", "petya", "fedya", "misha", "grisha"};
    private static String[] url = {"vk.com", "epam.com", "youtube.com", "gmail.com", "facebook.com"};

    private Path path;
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "login,url,timeSpend";

    CyclicBarrier barrier;

    public FileGenerator(Path path, CyclicBarrier barrier) {
        this.path = path;
        this.barrier = barrier;
    }

    @Override
    public void run() {

        Path file;
        try {
            if (!path.toFile().exists()) {
                file = Files.createFile(path);
            } else {
                file = Paths.get(path.toUri());
            }
            writeToFile(file.toString());

            barrier.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String fileName) {
        Random random = new Random();
        FileWriter fileWriter = null;
        try {

            fileWriter = new FileWriter(fileName);

            //Write the CSV file header
            fileWriter.append(FILE_HEADER);

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            for (int i = 0; i < 20; i++) {
                fileWriter.append(login[random.nextInt(4)]);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(url[random.nextInt(4)]);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(random.nextInt(60)));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }
}
