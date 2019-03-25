package com.epam.concurrency.homework1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class Util {

    public static BigDecimal generateRandomMoney() {
        return new BigDecimal(BigInteger.valueOf(new Random().nextInt(100001)), 2);
    }

}