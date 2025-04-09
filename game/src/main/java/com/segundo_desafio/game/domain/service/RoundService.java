package com.segundo_desafio.game.domain.service;

import com.segundo_desafio.game.domain.dto.RoundResponseDTO;
import com.segundo_desafio.game.domain.enums.Move;
import com.segundo_desafio.game.domain.validator.MoveValidator;
import com.segundo_desafio.game.domain.validator.RoundRequestValidator;
import com.segundo_desafio.game.persistence.model.MatchHistory;
import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import com.segundo_desafio.game.persistence.repository.MatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RoundService {

    private final MatchHistoryRepository repository;

    private final RoundRequestValidator roundRequestValidator;
    private final MoveValidator moveValidator;

    public RoundResponseDTO saveRound (RoundRequestDTO data){

        roundRequestValidator.validate(data);
        moveValidator.validate(data);

        MatchHistory history = new MatchHistory();
        history.setPlayerOneMove(data.playerOneMove().toUpperCase());
        history.setPlayerOneName(data.playerOneName());
        history.setPlayerTwoMove(data.playerTwoMove().toUpperCase());
        history.setPlayerTwoName(data.playerTwoName());
        history.setDate(LocalDateTime.now());

        repository.save(history);

        Move playerOneMove = Move.valueOf(data.playerOneMove().toUpperCase());
        Move playerTwoMove = Move.valueOf(data.playerTwoMove().toUpperCase());

        String winner = GameRulesService.checkWinner(playerOneMove, playerTwoMove);

        return new RoundResponseDTO(
                data.playerOneMove().toUpperCase(),
                data.playerOneName(),
                data.playerTwoMove().toUpperCase(),
                data.playerTwoName(),
                winner);
    }

}
