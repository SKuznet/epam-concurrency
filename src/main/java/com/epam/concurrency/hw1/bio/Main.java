package com.epam.concurrency.hw1.bio;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static int M = 13;
    public static int N = 4_000_000;

    public static void main(String[] args) throws IOException {

        String string = getString(N);
        System.out.println(string);
        StringReader reader = new StringReader(string);

        long[] window = new long[M / 31 + 1];
        int lastSectionSize = (M - (M / 31) * 31) * 2;

        long[] fill = fill(reader, window, M);
        WindowWrapper firstWindow = new WindowWrapper(fill);

        TreeMap<WindowWrapper, List<Integer>> map = new TreeMap<>();

        List<Integer> positions = new ArrayList<>();
        positions.add(0);
        map.put(firstWindow, positions);
        System.out.println(printWindow(window, 0, lastSectionSize));

        long[] put = copy(window);
        for (int j = 1; j < N - M; j++) {
            if (j % 1000000 == 0) {
                System.out.println(j);
            }
            put = put(reader.read(), put, lastSectionSize);
            WindowWrapper windowWrapper = new WindowWrapper(put);
            if (!map.containsKey(windowWrapper)) {
                positions = new ArrayList<>();
                positions.add(j);
                map.put(windowWrapper, positions);
            } else {
                positions = map.get(windowWrapper);
                positions.add(j);
            }
        }

        map.entrySet().stream()
                .forEach(entry -> {
                    if (entry.getValue().size() > 1) {
                        System.out.println(entry);
                    }
        });

    }

    private static long[] fill(StringReader reader, long[] window, int freeSpace) throws IOException {

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

    private static long[] put(int read, long[] window, int lastSectionSize) {
        long[] result = copy(window);
        if (result.length == 1) {
            long firstSection = result[0];
            firstSection = firstSection << 2;
            firstSection = addCodedLetter(read, firstSection);
            long filler = Long.MAX_VALUE << lastSectionSize;
            firstSection = firstSection | filler;
            result[0] = firstSection;
        } else {
            long bitsToMove = Long.MAX_VALUE;

            for (int i = result.length - 1; i >= 0; i--) {
                if (i == result.length - 1) {
                    long section = result[i];
                    bitsToMove = (section << (64 - lastSectionSize)) >>> 62;
                    section = section << 2;
                    section = addCodedLetter(read, section);
                    long filler = Long.MAX_VALUE << lastSectionSize;
                    section = section | filler;
                    result[i] = section;
                } else {
                    long section = result[i];
                    section = section << 2;
                    section |= bitsToMove;
                    bitsToMove = section >>> 62;
                    long filler = Long.MAX_VALUE << 62;
                    section = section | filler;
                    result[i] = section;
                }
            }
        }
        return result;
    }

    public static String printWindow(long[] window, int indent, int lastSectionSize) {
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
        return stringBuilder.toString();
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
                .parallel()
            .mapToObj(i -> "ACGT".charAt(new Random().nextInt(4)))
            .map(String::valueOf)
            .collect(Collectors.joining());
    }

    private static long[] copy(long[] window) {
        long[] mewArr = new long[window.length];
        for (int i = 0; i < window.length; i++) {
            mewArr[i] = window[i];
        }
        return mewArr;
    }
}
