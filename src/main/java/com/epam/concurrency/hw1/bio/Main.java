package com.epam.concurrency.hw1.bio;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        int N = 100;
        int M = 2;

        String string = getString(N);
        System.out.println(string);
        StringReader reader = new StringReader(string);

        Long[] window = new Long[M / 31 + 1];
        int lastSectionSize = (M - (M / 31) * 31) * 2;

        ArrayList list = new ArrayList();
        HashMap<Long[], List<Integer>> map = new HashMap<>();

        fill(reader, window, M);
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(0);
        map.put(window, integerArrayList);
//        list.add(Arrays.copyOf(window, window.length));
//        printWindow(window, 0, lastSectionSize);

        for (int j = 1; j < N - M; j++) {
            Long[] put = put(reader.read(), window, lastSectionSize);
            if (map.containsKey(put)) {
                List<Integer> integers = map.get(put);
                integers.add(j);
            } else {
                List<Integer> subList = new ArrayList<>();
                subList.add(j);
                map.put(window, subList);
            }
//            list.add(put);
//            printWindow(put, j, lastSectionSize);
        }

        System.out.println(map);

    }

    private static Long[] fill(StringReader reader, Long[] window, int freeSpace) throws IOException {

        for (int i = 0; i < window.length; i++) {
            long section = Long.MAX_VALUE;
            for (int j = 0; j < 31 && freeSpace > 0; j++) {
                section = section << 2;
                int read = reader.read();
                section = addCodedLetter(read, section);
                freeSpace--;
            }
            window[i] = section;
        }
        return window;
    }

    private static Long[] put(int read, Long[] window, int lastSectionSize) {
        if (window.length == 1) {
            long firstSection = window[0];
            firstSection = firstSection << 2;
            firstSection = addCodedLetter(read, firstSection);
            window[0] = firstSection;
        } else {
            long bitsToMove = Long.MAX_VALUE;

            for (int i = window.length - 1; i >= 0; i--) {
                if (i == window.length - 1) {
                    long section = window[i];
                    bitsToMove = (section << (64 - lastSectionSize)) >>> 62;
                    section = section << 2;
                    section = addCodedLetter(read, section);
                    long filler = Long.MAX_VALUE << lastSectionSize;
                    section = section | filler;
                    window[i] = section;
                } else {
                    long section = window[i];
                    section = section << 2;
                    section |= bitsToMove;
                    bitsToMove = section >>> 62;
                    long filler = Long.MAX_VALUE << 62;
                    section = section | filler;
                    window[i] = section;
                }
            }
        }
        return window;
    }

    private static void printWindow(Long[] window, int indent, int lastSectionSize) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            stringBuilder.append("_");
        }

        for (int i = 0; i < window.length; i++) {
            long section = window[i];
            int sectionCount = i == window.length - 1 ? lastSectionSize / 2 : 31;
            char[] chars = Long.toBinaryString(section).toCharArray();

            StringBuilder sb = new StringBuilder();
            for (int j = chars.length - 1; j >= 2 && sectionCount > 0; j -= 2) {
                if (chars[j - 1] == '0' && chars[j] == '0') {
                    sb.append("A");
                }
                if (chars[j - 1] == '0' && chars[j] == '1') {
                    sb.append("C");
                }
                if (chars[j - 1] == '1' && chars[j] == '0') {
                    sb.append("G");
                }
                if (chars[j - 1] == '1' && chars[j] == '1') {
                    sb.append("T");
                }
                sectionCount--;
            }
            stringBuilder.append(sb.reverse());
        }
        System.out.println(stringBuilder.toString());
    }

    private static long addCodedLetter(int read, long section) {
        int A = 'A';
        int C = 'C';
        int G = 'G';
        int T = 'T';
        if ((A ^ read) == 0) {
            section |= 0b00;
        } else if ((C ^ read) == 0) {
            section |= 0b01;
        } else if ((G ^ read) == 0) {
            section |= 0b10;
        } else if ((T ^ read) == 0) {
            section |= 0b11;
        }
        return section;
    }

    private static String getString(int count ) {
        return IntStream.range(0, count)
            .mapToObj(i -> "ACGT".charAt(new Random().nextInt(4)))
            .map(String::valueOf)
            .collect(Collectors.joining());
    }
}
