package com.epam.concurrency.homework2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Client> clientsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            clientsList.add(new Client(i + 1));
        }
        executorService.invokeAll(clientsList);
        executorService.shutdownNow();

    }
}
