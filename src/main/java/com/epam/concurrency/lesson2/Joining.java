package com.epam.concurrency.lesson2;

public class Joining {

    public static void main(String[] args) {
        Sleeper tony = new Sleeper("tony", 10000);
        Sleeper stark = new Sleeper("stark", 3000);

        Joiner klop = new Joiner("klop", tony);
        Joiner blob = new Joiner("blob", stark);
        stark.interrupt();
    }
}
