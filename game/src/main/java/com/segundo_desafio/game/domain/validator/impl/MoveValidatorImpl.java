package com.segundo_desafio.game.domain.validator.impl;

import com.segundo_desafio.game.api.model.exception.InvalidGameRequestException;
import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import com.segundo_desafio.game.domain.enums.Move;
import com.segundo_desafio.game.domain.validator.MoveValidator;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.EnumUtils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@Component
public class MoveValidatorImpl implements MoveValidator {

    private final Logger logger = LogManager.getLogger(MoveValidatorImpl.class.getName());

    @Override
    public void validate (RoundRequestDTO dto){

        if (!StringUtils.isNotBlank(dto.playerOneMove()) || !EnumUtils.isValidEnum(Move.class,dto.playerOneMove().toUpperCase()) ) {
            logger.error("Not valid enum option");
            throw new InvalidGameRequestException("Not valid enum option!");
        }
        if (!StringUtils.isNotBlank(dto.playerTwoMove()) || !EnumUtils.isValidEnum(Move.class,dto.playerTwoMove().toUpperCase())) {
            logger.error("Not valid enum option");
            throw new InvalidGameRequestException("Not valid enum option!");
        }

    }


}
