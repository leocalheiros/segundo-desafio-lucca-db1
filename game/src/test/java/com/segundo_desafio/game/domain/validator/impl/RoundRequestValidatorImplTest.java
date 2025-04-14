package com.segundo_desafio.game.domain.validator.impl;

import com.segundo_desafio.game.api.model.exception.InvalidGameRequestException;
import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import com.segundo_desafio.game.domain.validator.RoundRequestValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundRequestValidatorImplTest {

    private final RoundRequestValidator roundRequestValidator = new RoundRequestValidatorImpl();

    @Test
    void shouldNotThrowWhenPlayersNameAreValid(){
        RoundRequestDTO dto = new RoundRequestDTO("ROCK", "Lucca", "PAPER", "Bot");

        assertDoesNotThrow(() -> roundRequestValidator.validate(dto));
    }

    @Test
    void shouldThrowWhenPlayerOneNameIsNull(){

        RoundRequestDTO dto = new RoundRequestDTO("ROCK", null, "PAPER", "Bot");

        assertThrows(InvalidGameRequestException.class, () -> roundRequestValidator.validate(dto));

    }
    @Test
    void shouldThrowWhenPlayerOneNameIsEmpty(){

        RoundRequestDTO dto = new RoundRequestDTO("ROCK", "", "PAPER", "Bot");

        assertThrows(InvalidGameRequestException.class, () -> roundRequestValidator.validate(dto));

    }
    @Test
    void shouldThrowWhenPlayerTwoNameIsNull(){

        RoundRequestDTO dto = new RoundRequestDTO("ROCK", "null", "PAPER", null);

        assertThrows(InvalidGameRequestException.class, () -> roundRequestValidator.validate(dto));

    }

    @Test
    void shouldThrowWhenPlayerTwoNameIsEmpty(){

        RoundRequestDTO dto = new RoundRequestDTO("ROCK", "null", "PAPER", "");

        assertThrows(InvalidGameRequestException.class, () -> roundRequestValidator.validate(dto));

    }


}