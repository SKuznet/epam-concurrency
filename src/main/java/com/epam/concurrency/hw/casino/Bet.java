package com.epam.concurrency.hw.casino;

public class Bet {
    public int player_id;
    public int place;
    public int bet;
    Bet(int player_id, int place, int bet) {
        this.place = place;
        this.player_id = player_id;
        this.bet = bet;
    }
}
