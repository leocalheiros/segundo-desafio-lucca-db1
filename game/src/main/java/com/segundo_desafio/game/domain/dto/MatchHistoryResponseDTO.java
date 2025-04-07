package com.segundo_desafio.game.domain.dto;

import java.time.LocalDateTime;

public record MatchHistoryResponseDTO(
        String playerOneMove,
        String playerOneName,
        String playerTwoMove,
        String playerTwoName,
        LocalDateTime date,
        String result

) {}
