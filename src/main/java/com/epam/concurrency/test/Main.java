package com.epam.concurrency.test;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        int i = game.count++;
        System.out.println(i);
        Game.count++;
        System.out.println(i);
        System.out.println(Game.count);
    }
}
