package com.epam.concurrency.lesson7;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        char[] chars = new char[]{'A', 'B', 'C'};
        System.out.println(chars);
        Map map = new HashMap();
        map.put(chars, chars);


        chars[1] = 'D';
        map.put(chars, chars);
        System.out.println(map);

        char[] newChars = chars;
        newChars[1] = 'W';
        map.put(newChars, newChars);
        System.out.println(1);

    }
}
