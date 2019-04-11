package com.epam.concurrency.homework6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class CsvFileReader {
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";

    //Student attributes index
    private static final int LOGIN_IDX = 0;
    private static final int URL_IDX = 1;
    private static final int TIMESPEND_IDX = 2;

    public static void readCsvFile(String fileName) {

        BufferedReader fileReader = null;

        try {
            ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
            //Create a new list of student to be filled by CSV file data


            String line = "";

            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    //Create a new fileLine object and fill his  data
                    String fileLine = tokens[LOGIN_IDX] + " " + tokens[URL_IDX] + " " + tokens[TIMESPEND_IDX];
                    if (map.get(tokens[LOGIN_IDX] + " " + tokens[URL_IDX]) != null) {
                        int time = map.get(tokens[LOGIN_IDX] + " " + tokens[URL_IDX]);
                        time += Integer.parseInt(tokens[TIMESPEND_IDX]);
                        map.put(tokens[LOGIN_IDX] + " " + tokens[URL_IDX], time);
                    } else {
                        map.put(tokens[LOGIN_IDX] + " " + tokens[URL_IDX], Integer.parseInt(tokens[TIMESPEND_IDX]));
                    }
                }
            }

            int k = 0;
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }

    }
}
