package com.segundo_desafio.game.domain.service;



import com.segundo_desafio.game.persistence.model.MatchHistory;
import com.segundo_desafio.game.domain.dto.MatchHistoryResponseDTO;
import com.segundo_desafio.game.domain.enums.Move;
import com.segundo_desafio.game.persistence.repository.MatchHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class MatchHistoryService {



    private final MatchHistoryRepository repository;

    public MatchHistoryService(MatchHistoryRepository repository){
        this.repository = repository;
    }

    private final Logger logger = LogManager.getLogger(MatchHistoryService.class.getName());

    public List<MatchHistoryResponseDTO> getAllRounds(){
        List<MatchHistory> findAll = repository.findAll();
        logger.info("History from db: {}", findAll );

        return findAll.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private MatchHistoryResponseDTO toResponseDTO(MatchHistory history){

        Move playerOneMove = Move.valueOf(history.getPlayerOneMove().toUpperCase());
        Move playerTwoMove = Move.valueOf(history.getPlayerTwoMove().toUpperCase());
        String result = GameRulesService.checkWinner(playerOneMove, playerTwoMove);

        return new MatchHistoryResponseDTO(
                history.getPlayerOneMove(),
                history.getPlayerOneName(),
                history.getPlayerTwoMove(),
                history.getPlayerTwoName(),
                history.getDate(),
                result
        );
    }
}
