package com.epam.concurrency.hw2;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testSwap() {
        Main main = new Main();
        int a = 1;
        int b = 2;
        int[] array = new int[]{0, 1, 2};
        new Main.QuickSort().swap(array, a, b);
        assertArrayEquals(new int[]{0, 2, 1}, array);
    }

}