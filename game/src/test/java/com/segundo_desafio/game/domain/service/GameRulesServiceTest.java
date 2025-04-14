package com.segundo_desafio.game.domain.service;

import com.segundo_desafio.game.domain.enums.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameRulesServiceTest {

    @Test
    void assertShouldReturnPlayerOneWinsRockVsScissors(){
        var result = GameRulesService.checkWinner(Move.ROCK, Move.SCISSORS);
        assertEquals("PLAYER_ONE_WINS", result);
    }
    @Test
    void assertShouldReturnPlayerOneWinsPaperVsRock(){
        var result = GameRulesService.checkWinner(Move.PAPER, Move.ROCK);
        assertEquals("PLAYER_ONE_WINS", result);
    }
    @Test
    void assertShouldReturnPlayerOneWinsScissorsVsPaper(){
        var result = GameRulesService.checkWinner(Move.SCISSORS, Move.PAPER);
        assertEquals("PLAYER_ONE_WINS", result);
    }

    @Test
    void assertShouldReturnPlayerTwoWinsRockVsScissors(){
        var result = GameRulesService.checkWinner(Move.SCISSORS, Move.ROCK);
        assertEquals("PLAYER_TWO_WINS", result);
    }
    void assertShouldReturnPlayerTwoWinsPaperVsRock(){
        var result = GameRulesService.checkWinner(Move.ROCK, Move.PAPER);
        assertEquals("PLAYER_TWO_WINS", result);
    }

    void assertShouldReturnPlayerTwoWinsScissorsVsPaper(){
        var result = GameRulesService.checkWinner(Move.PAPER, Move.SCISSORS);
        assertEquals("PLAYER_TWO_WINS", result);
    }
    @Test
    void assertShouldReturnDrawRock(){
        var result = GameRulesService.checkWinner(Move.ROCK, Move.ROCK);
        assertEquals("DRAW", result);
    }
    @Test
    void assertShouldReturnDrawPaper(){
        var result = GameRulesService.checkWinner(Move.PAPER, Move.PAPER);
        assertEquals("DRAW", result);
    }
    @Test
    void assertShouldReturnDrawScissors(){
        var result = GameRulesService.checkWinner(Move.SCISSORS, Move.SCISSORS);
        assertEquals("DRAW", result);
    }

}