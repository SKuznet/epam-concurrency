package com.epam.concurrency.lesson2;

public class Joining {
    public static void main(String[] args) {
        Sleeper tony = new Sleeper("Tony", 1500);
        Sleeper stark = new Sleeper("Stark", 1500);

        Joiner klop = new Joiner("klop", tony);
        Joiner blob = new Joiner("blob", stark);

        stark.interrupt();
    }
}
