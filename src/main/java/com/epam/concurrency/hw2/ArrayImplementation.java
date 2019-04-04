package com.epam.concurrency.hw2;

import com.epam.concurrency.hw1.bio.LongArrayComparator;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayImplementation {

        public static int M = 10;
        public static int N = 2_000;

        public static void main(String[] args) throws IOException {

            String string = getString(N);
            char[] chars = string.toCharArray();
            shuffleArray(chars);
            shuffleArray(chars);
            shuffleArray(chars);
            shuffleArray(chars);
            shuffleArray(chars);
            shuffleArray(chars);
            string = String.valueOf(chars);
            chars = null;
            System.out.println(string);
            StringReader reader = new StringReader(string);

            long[] window = new long[M / 31 + 2];
            int lastSectionSize = (M - (M / 31) * 31) * 2;

            long[] fill = fill(reader, window, M);

            List<long[]> list = new ArrayList<>();
            list.add(fill);

            long[] put = copy(window);
            for (int j = 1; j < N - M; j++) {
                if (j % 100000 == 0) {
                    System.out.println(j);
                }
                put = put(reader.read(), put, lastSectionSize);
                put[put.length - 1] = j;
                list.add(put);
            }



            System.out.println("done");

        }


        static void shuffleArray(char[] ar) {
            Random rnd = ThreadLocalRandom.current();
            for (int i = ar.length - 1; i > 0; i--)
            {
                int index = rnd.nextInt(i + 1);
                char a = ar[index];
                ar[index] = ar[i];
                ar[i] = a;
            }
        }

        private static long[] fill(StringReader reader, long[] window, int freeSpace) throws IOException {

            for (int i = 0; i < window.length - 1; i++) {
                long section = Long.MAX_VALUE;
                for (int j = 0; j < 31 && freeSpace > 0; j++) {
                    section = section << 2;
                    int read = reader.read();
                    section = addCodedLetter(read, section);
                    freeSpace--;
                }
                window[i] = section;
            }
            window[window.length - 1] = 0;
            return window;
        }

        private static long[] put(int read, long[] window, int lastSectionSize) {
            long[] result = copy(window);
            if (result.length == 2) {
                long firstSection = result[0];
                firstSection = firstSection << 2;
                firstSection = addCodedLetter(read, firstSection);
                long filler = Long.MAX_VALUE << lastSectionSize;
                firstSection = firstSection | filler;
                result[0] = firstSection;
            } else {
                long bitsToMove = Long.MAX_VALUE;

                for (int i = result.length - 2; i >= 0; i--) {
                    if (i == result.length - 2) {
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

            for (int i = 0; i < window.length - 2; i++) {
                long section = window[i];
                int sectionCount = i == window.length - 2 ? lastSectionSize / 2 : 31;
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
                    .mapToObj(i -> "ACGT".charAt(ThreadLocalRandom.current().nextInt(4)))
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
