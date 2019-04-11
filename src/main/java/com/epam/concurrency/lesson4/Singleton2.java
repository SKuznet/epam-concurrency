package com.epam.concurrency.lesson4;

public class Singleton2 {
    private static Singleton2 ourInstance = new Singleton2();

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        return ourInstance;
    }
}
