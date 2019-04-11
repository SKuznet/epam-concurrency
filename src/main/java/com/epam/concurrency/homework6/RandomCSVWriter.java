package com.epam.concurrency.homework6;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomCSVWriter {

    private String[] people = new String[]{"Vasya", "Petya", "Borya", "Semyon", "Luq"};

    private String[] sites = new String[]{"vk.com", "ok.com", "epam.com", "fb.com", "instagram.com"};

    Random random = new Random();

    public void writeRandomRow() throws IOException {
        String[] record = new String[20];

        for(int j = 0; j < 20; j++){
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


        String csvFileName = "data.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csvFileName));

        writer.writeNext(record);
        writer.close();
    }
}
