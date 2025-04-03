package com.segundo_desafio.api.domain.round;

public record RoundResponseDTO(
        String playerOneMove,
        String playerOneName,
        String playerTwoMove,
        String playerTwoName,
        String result
) {

}
