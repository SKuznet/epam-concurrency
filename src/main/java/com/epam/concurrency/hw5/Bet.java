package com.epam.concurrency.hw5;

public class Bet {

    int playerId;
    int number;
    int dineros;

    public Bet(int playerId, int number, int dineros) {
        this.playerId = playerId;
        this.number = number;
        this.dineros = dineros;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getNumber() {
        return number;
    }

    public int getDineros() {
        return dineros;
    }
}
