package com.epam.concurrency.homework6;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomCSVWriter {

    private String[] people = new String[]{"Vasya", "Petya", "Borya", "Semyon", "Luq"};

    private String[] sites = new String[]{"vk.com", "ok.com", "epam.com", "fb.com", "instagram.com"};

    private Random random = new Random();

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public synchronized void createCsvFiles(int quantityOfFiles) throws IOException {
        if (!new File("temp/in").exists()) {
            new File("temp/in").mkdirs();
        }
        for (int i = 0; i < quantityOfFiles; i++) {
            if (atomicInteger.get() <= 50) {
                writeRandomRowFile("temp/in/data" + atomicInteger.getAndIncrement() + ".csv");
            }
        }
    }

    private void writeRandomRowFile(String csvFileName) throws IOException {
        String[] record = new String[20];

        for (int j = 0; j < 20; j++) {
            StringBuilder sb = new StringBuilder();
            int numOfPerson = random.nextInt(5);
            int numOfSite = random.nextInt(5);
            int timeOnSite = random.nextInt(590) + 10;
            sb.append("<session>");
            sb.append("<name>" + people[numOfPerson] + "</name>");
            sb.append("<site>" + sites[numOfSite] + "</site>");
            sb.append("<time>" + timeOnSite + "</time>");
            sb.append("</session>");
            record[j] = sb.toString();
        }

        CSVWriter writer = new CSVWriter(new FileWriter(csvFileName));

        writer.writeNext(record);
        writer.close();
    }
}
