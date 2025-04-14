package com.segundo_desafio.game.domain.service;

import com.segundo_desafio.game.domain.enums.Move;

import java.util.Map;

public class GameRulesService {

    private static final Map<Move, Move> gameLogic = Map.of(
            Move.ROCK, Move.SCISSORS,
            Move.PAPER, Move.ROCK,
            Move.SCISSORS, Move.PAPER
    );


    public static String checkWinner(Move playerOneMove, Move playerTwoMove) {
        if (playerOneMove == playerTwoMove)return "DRAW";
        if(gameLogic.get(playerOneMove) == playerTwoMove) return "PLAYER_ONE_WINS";
        return "PLAYER_TWO_WINS";
    }
}
