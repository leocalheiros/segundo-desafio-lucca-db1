package com.segundo_desafio.game.domain.validator.impl;

import com.segundo_desafio.game.api.model.exception.InvalidGameRequestException;
import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import com.segundo_desafio.game.domain.enums.Move;
import com.segundo_desafio.game.domain.validator.MoveValidator;
import org.springframework.stereotype.Component;

@Component
public class MoveValidatorImpl implements MoveValidator {


    @Override
    public void validate (RoundRequestDTO dto){

        if (dto.playerOneMove() == null || dto.playerOneMove().isEmpty()) {
            throw new InvalidGameRequestException("Player one move is required");
        }
        if (dto.playerTwoMove() == null || dto.playerTwoMove().isEmpty()) {
            throw new InvalidGameRequestException("Player two move is required");
        }

        try {
            Move.valueOf(dto.playerOneMove().toUpperCase());
            Move.valueOf(dto.playerTwoMove().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidGameRequestException("One or both moves are invalid (must be ROCK, PAPER or SCISSORS)");
        }


    }


}
