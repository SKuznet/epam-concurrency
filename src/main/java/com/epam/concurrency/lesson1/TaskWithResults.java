package com.epam.concurrency.lesson1;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TaskWithResults implements Callable<String> {

    private int id;

    public TaskWithResults(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        return "result TaskWithResult " + id;
    }
}
