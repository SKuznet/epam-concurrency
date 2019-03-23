package com.epam.concurrency.hw1.bank;

public class LowBalanceException extends RuntimeException {

    public LowBalanceException() {
        super();
    }

    public LowBalanceException(String message) {
    }
}
