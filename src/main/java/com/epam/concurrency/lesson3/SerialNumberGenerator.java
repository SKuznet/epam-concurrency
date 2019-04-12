package com.epam.concurrency.lesson3;

public class SerialNumberGenerator {

    private static volatile int serialNumber = 0;

    public static int nextSerialNumber() {
        return serialNumber++;
    }

}