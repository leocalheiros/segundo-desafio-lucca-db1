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
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
@RequiredArgsConstructor
public class RoundService {

    private final MatchHistoryRepository repository;

    private final RoundRequestValidator roundRequestValidator;
    private final MoveValidator moveValidator;

    private static final Logger logger = LogManager.getLogger(RoundService.class.getName());

    public RoundResponseDTO saveRound (RoundRequestDTO data){
        logger.info("Received data:{} ", data);

        roundRequestValidator.validate(data);
        moveValidator.validate(data);

        MatchHistory history = new MatchHistory();
        history.setPlayerOneMove(data.playerOneMove().toUpperCase());
        history.setPlayerOneName(data.playerOneName());
        history.setPlayerTwoMove(data.playerTwoMove().toUpperCase());
        history.setPlayerTwoName(data.playerTwoName());
        history.setDate(LocalDateTime.now());

        repository.save(history);
        logger.info("The match was saved in the database: ");
        Move playerOneMove = Move.valueOf(data.playerOneMove().toUpperCase());
        Move playerTwoMove = Move.valueOf(data.playerTwoMove().toUpperCase());

        String winner = GameRulesService.checkWinner(playerOneMove, playerTwoMove);
        RoundResponseDTO res = new RoundResponseDTO(
                data.playerOneMove().toUpperCase(),
                data.playerOneName(),
                data.playerTwoMove().toUpperCase(),
                data.playerTwoName(),
                winner);
        logger.info("Response: {}", res);
        return res;
    }

}
