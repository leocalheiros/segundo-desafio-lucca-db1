package com.segundo_desafio.game.domain.validator;

import com.segundo_desafio.game.api.model.exception.InvalidGameRequestException;
import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import com.segundo_desafio.game.domain.enums.Move;

public interface MoveValidator {

    void validate (RoundRequestDTO dto) throws InvalidGameRequestException;
}
