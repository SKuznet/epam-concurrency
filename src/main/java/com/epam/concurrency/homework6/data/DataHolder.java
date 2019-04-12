package com.epam.concurrency.homework6.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DataHolder {
    private static final List<Employee> employees;
    private static final List<URL> urls;

    private static final BlockingQueue<String> FILE_NAMES_QUEUE = new ArrayBlockingQueue<>(50, true);

    static {
        employees = Collections.unmodifiableList(Arrays.asList(
                new Employee("CHAOS LORD"),
                new Employee("mygodits2am"),
                new Employee("java rules"),
                new Employee("whereami"),
                new Employee("sudo -su makesandwich")));
        urls = Collections.unmodifiableList(Arrays.asList(
                new URL("epam.com"),
                new URL("youtube.com"),
                new URL("habr.com"),
                new URL("stackoverflow.com"),
                new URL("bleb.org")));
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static List<URL> getUrls() {
        return urls;
    }

    public static BlockingQueue<String> getFileNamesQueue() {
        return FILE_NAMES_QUEUE;
    }
}
