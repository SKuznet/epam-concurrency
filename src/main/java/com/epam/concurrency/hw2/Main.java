package com.epam.concurrency.hw2;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        int[] randomArray = getRandomArray(10);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue = Arrays.stream(randomArray)
                .collect(PriorityQueue::new, PriorityQueue::add, PriorityQueue::addAll);
        for (int i : queue) {
            System.out.println(i);
        }

        QuickSort quickSort = new QuickSort();
        quickSort.sort(randomArray);
    }

    private static int[] getRandomArray(int i) {
        return IntStream.generate(() -> new Random().nextInt(10)).limit(i).toArray();
    }

    static class QuickSort {

        public int[] sort(int[] randomArray) {
            quickSort(randomArray, 0, randomArray.length - 1);
            return new int[0];
        }

        private int[] quickSort(int[] array, int low, int high) {
            System.out.println(Arrays.toString(array));
            int partitionElement = 0;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[partitionElement]) {
                    swap(array, partitionElement, i);
                    partitionElement = i;
                }
            }
            System.out.println(Arrays.toString(array));
            return null;
        }

        public void swap(int[] array, int partitionElement, int next) {
            array[partitionElement] = array[partitionElement] + array[next];
            array[next] = array[partitionElement] - array[next];
            array[partitionElement] = array[partitionElement] - array[next];
        }


    }
}
