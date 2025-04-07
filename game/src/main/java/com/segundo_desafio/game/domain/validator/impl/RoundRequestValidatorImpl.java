package com.segundo_desafio.game.domain.validator.impl;

import com.segundo_desafio.game.api.model.exception.InvalidGameRequestException;
import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import com.segundo_desafio.game.domain.validator.RoundRequestValidator;
import org.springframework.stereotype.Component;

@Component
public class RoundRequestValidatorImpl implements RoundRequestValidator {

    @Override
    public void validate(RoundRequestDTO dto){
        if(dto.playerOneName() == null || dto.playerOneName().isEmpty()){
            throw new InvalidGameRequestException("Player one name cannot be null or empty");
        }
        if(dto.playerTwoName() == null || dto.playerTwoName().isEmpty()){
            throw new InvalidGameRequestException("Player two name cannot be null or empty");
        }
    }

}

