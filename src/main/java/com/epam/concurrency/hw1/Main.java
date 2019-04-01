package com.epam.concurrency.hw1;

import com.epam.concurrency.hw1.bank.Account;
import com.epam.concurrency.hw1.bank.Atm;
import com.epam.concurrency.hw1.bank.DangerousAtm;
import com.epam.concurrency.hw1.bank.SafeAtm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * summ in Account is 1MM.
 * 4 girls try to withdraw 70k times 4$ each time.
 * Together they going to withdraw 4 * 70k * 4 = 1.12M
 * <p>
 * DangerousAtm does not have synchronization. SafeAtm does.
 * So in case with DangerousAtm bank going to loose 120k$.
 * Is case with SafeAtm money are safe and sound.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Atm dangerousAtm = new DangerousAtm();
        Atm safeAtm = new SafeAtm();
        testAtm(dangerousAtm);
    }

    private static void testAtm(Atm atm) throws InterruptedException {
        Account account = new Account(new BigDecimal(1000000));
        Person Маша = new Person(account);
        Person Даша = new Person(account);
        Person Катя = new Person(account);
        Person Оля = new Person(account);

        CompletableFuture future = CompletableFuture.allOf(null);
        future.get

        List<Person> people = new ArrayList<>();
        people.add(Маша);
        people.add(Даша);
        people.add(Катя);
        people.add(Оля);

        people.stream()
            .peek(person -> person.setAtm(atm))
            .map(person -> new Thread(() -> {
                for (int j = 0; j < 80000; j++) {
                    person.withdrawMoney(new BigDecimal(4));
                }
            }))
            .forEach(Thread::start);

        Thread.sleep(3000);

        double sum = people.stream().mapToDouble(person -> person.getPocket().doubleValue()).sum();
        System.out.println("retrieved sum by girls: " + sum);

    }
}
