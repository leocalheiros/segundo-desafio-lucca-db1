package com.segundo_desafio.game.domain.validator.impl;

import com.segundo_desafio.game.api.model.exception.InvalidGameRequestException;
import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import com.segundo_desafio.game.domain.validator.MoveValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveValidatorImplTest {

    private final MoveValidator moveValidator = new MoveValidatorImpl();

    @Test
    void shouldNotThrowWhenMovesAreValid(){
        RoundRequestDTO dto = new RoundRequestDTO("ROCK", "Lucca", "PAPER", "Bot");

        assertDoesNotThrow(() -> moveValidator.validate(dto));
    }

    @Test
    void shouldThrowWhenPlayerOneMoveIsNull(){

        RoundRequestDTO dto = new RoundRequestDTO(null, "Lucca", "PAPER", "Bot");
        assertThrows(InvalidGameRequestException.class, () -> moveValidator.validate(dto));

    }

    @Test
    void shouldThrowWhenPlayerOneMoveIsEmpty(){
        RoundRequestDTO dto = new RoundRequestDTO("", "Lucca", "PAPER", "Bot");

        assertThrows(InvalidGameRequestException.class, () -> moveValidator.validate(dto));

    }

    @Test
    void shouldThrowWhenPlayerTwoMoveIsNull(){

        RoundRequestDTO dto = new RoundRequestDTO("ROCK", "Lucca", null, "Bot");
        assertThrows(InvalidGameRequestException.class, () -> moveValidator.validate(dto));

    }

    @Test
    void shouldThrowWhenPlayerTwoMoveIsEmpty(){
        RoundRequestDTO dto = new RoundRequestDTO("ROCK", "Lucca", "", "Bot");

        assertThrows(InvalidGameRequestException.class, () -> moveValidator.validate(dto));

    }


    @Test
    void shouldThrowWhenPlayerOneMoveIsInvalid(){
        RoundRequestDTO dto = new RoundRequestDTO("RO", "Lucca", "ROCK", "Bot");

        assertThrows(InvalidGameRequestException.class, () -> moveValidator.validate(dto));

    }

    @Test
    void shouldThrowWhenPlayerTwoMoveIsInvalid(){
        RoundRequestDTO dto = new RoundRequestDTO("ROCK", "Lucca", "ROK", "Bot");

        assertThrows(InvalidGameRequestException.class, () -> moveValidator.validate(dto));

    }
}