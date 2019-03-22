package com.epam.concurrency.lesson2;

public class Joining {
    public static void main(String[] args) {
        Sleeper tony = new Sleeper("tony", 1500);
        Sleeper stark = new Sleeper("stark", 1500);

        Joiner klop = new Joiner("klop", tony);
        Joiner blop = new Joiner("blop", stark);
        stark.interrupt();
    }
}
