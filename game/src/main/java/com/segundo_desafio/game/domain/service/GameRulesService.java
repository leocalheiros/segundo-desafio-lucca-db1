package com.segundo_desafio.game.domain.service;

import com.segundo_desafio.game.domain.enums.Move;

public class GameRulesService {

    private static boolean gameLogic(Move move, Move otherMove) {
        return (move == Move.ROCK && otherMove == Move.SCISSORS) ||
                (move == Move.PAPER && otherMove == Move.ROCK) ||
                (move == Move.SCISSORS && otherMove == Move.PAPER);
    }

    public static String checkWinner(Move playerOneMove, Move playerTwoMove) {
        if (gameLogic(playerOneMove, playerTwoMove)) {
            return "PLAYER_ONE_WINS";
        } else if (gameLogic(playerTwoMove, playerOneMove)) {
            return "PLAYER_TWO_WINS";
        } else {
            return "DRAW";
        }
    }
}
