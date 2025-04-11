package com.segundo_desafio.game.domain.validator.impl;

import com.segundo_desafio.game.api.model.exception.InvalidGameRequestException;
import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import com.segundo_desafio.game.domain.validator.RoundRequestValidator;
import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class RoundRequestValidatorImpl implements RoundRequestValidator {

    private static final String NOT_VALID_PLAYER_NAME_ERROR = "Player %s name cannot be null or empty";
    private final Logger logger = LogManager.getLogger(RoundRequestValidatorImpl.class.getName());

    @Override
    public void validate(RoundRequestDTO dto){
        if(!StringUtils.isNotBlank(dto.playerOneName())){
            logger.error("Player one name cannot be null or empty");
            throw new InvalidGameRequestException(String.format(NOT_VALID_PLAYER_NAME_ERROR, "one"));
        }
        if(!StringUtils.isNotBlank(dto.playerTwoName())){
            logger.error("Player two name cannot be null or empty");
            throw new InvalidGameRequestException(String.format(NOT_VALID_PLAYER_NAME_ERROR, "two"));
        }
    }

}

