package com.epam.concurrency.homework6;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        RandomCSVWriter randomCSVWriter = new RandomCSVWriter();
        try {
            randomCSVWriter.writeRandomRow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
