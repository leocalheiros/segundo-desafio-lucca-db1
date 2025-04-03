package com.segundo_desafio.api.model;

public enum Move {
    ROCK("Rock"),
    PAPER("Paper"),
    SCISSORS("Scissors");

    private final String newMove;

    Move(String move) {
        this.newMove = move;
    }

    public String getMove() {
        return newMove;
    }

    @Override
    public String toString() {
        return newMove;
    }
}
