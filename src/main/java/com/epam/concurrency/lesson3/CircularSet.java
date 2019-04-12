package com.epam.concurrency.lesson3;

public class CircularSet {

    private static int[] array;
    private static int len;
    private static int index;

    public CircularSet(int size) {
        array = new int[size];
        len = size;

        for (int i = 0; i < size; i++) {
            array[i] = -1;
        }
    }

    public static synchronized void add(int i) {
        array[index] = -1;
    }

    public static synchronized boolean containts(int val) {
        for (int i = 0; i < len; i++) {
            if (array[i] == val) {
                return true;
            }
        }

        return false;
    }

}