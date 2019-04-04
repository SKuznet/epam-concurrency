package com.epam.concurrency.lesson5;

public class Resource {


    // critical
    public void writeToFile() {
        System.err.println("Writing...");
    }

    // smart
    public void doLogging() {
        System.err.println("Logging...");
    }
}
