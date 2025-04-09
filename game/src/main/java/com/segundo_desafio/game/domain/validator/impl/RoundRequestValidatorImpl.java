package com.segundo_desafio.game.domain.validator.impl;

import com.segundo_desafio.game.api.model.exception.InvalidGameRequestException;
import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import com.segundo_desafio.game.domain.validator.RoundRequestValidator;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class RoundRequestValidatorImpl implements RoundRequestValidator {

    @Override
    public void validate(RoundRequestDTO dto){
        if(!StringUtils.isNotBlank(dto.playerOneName())){
            throw new InvalidGameRequestException("Player one name cannot be null or empty");
        }
        if(!StringUtils.isNotBlank(dto.playerTwoName())){
            throw new InvalidGameRequestException("Player two name cannot be null or empty");
        }
    }

}

