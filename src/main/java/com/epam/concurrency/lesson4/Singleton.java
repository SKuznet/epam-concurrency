package com.epam.concurrency.lesson4;

public class Singleton {
    private volatile static Singleton ourInstance;
    private static String name;

    private Singleton() {
    }

    public static Singleton getInstance() {

        // don't do that!!!
        if (ourInstance == null) {
            synchronized (Singleton.class) {
                if (ourInstance == null) {
                    ourInstance = new Singleton();
                }
            }
        }

        return ourInstance;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Singleton.name = name;
    }
}
