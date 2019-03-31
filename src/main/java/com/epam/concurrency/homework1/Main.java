package com.epam.concurrency.homework1;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) {
    User user1 = new User(new BigDecimal(500), "Jack");
    User user2 = new User(new BigDecimal(1000), "Bill");
    User user3 = new User(new BigDecimal(200), "Sam");
    ATM atm = new ATM();

    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      executorService.execute(() -> atm.getMoney(user1, new BigDecimal(100)));
      executorService.execute(() -> atm.getMoney(user2, new BigDecimal(100)));
      executorService.execute(() -> atm.getMoney(user3, new BigDecimal(10)));
    }
    executorService.shutdown();
  }
}
