package com.segundo_desafio.game.domain.validator;

import com.segundo_desafio.game.api.model.exception.InvalidGameRequestException;
import com.segundo_desafio.game.domain.dto.RoundRequestDTO;

public interface RoundRequestValidator {

    void validate(RoundRequestDTO dto) throws InvalidGameRequestException;
}
