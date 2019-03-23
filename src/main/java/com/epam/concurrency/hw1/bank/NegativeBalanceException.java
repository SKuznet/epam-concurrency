package com.epam.concurrency.hw1.bank;

public class NegativeBalanceException extends RuntimeException {

    public NegativeBalanceException() {
        super();
    }

    public NegativeBalanceException(String message) {
        super(message);
    }
}
