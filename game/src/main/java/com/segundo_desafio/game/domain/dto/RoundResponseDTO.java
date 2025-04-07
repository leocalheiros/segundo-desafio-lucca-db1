package com.segundo_desafio.game.domain.dto;

public record RoundResponseDTO(
        String playerOneMove,
        String playerOneName,
        String playerTwoMove,
        String playerTwoName,
        String result
) {

}
