package com.segundo_desafio.game.domain.service;


import com.segundo_desafio.game.persistence.model.MatchHistory;
import com.segundo_desafio.game.domain.dto.MatchHistoryResponseDTO;
import com.segundo_desafio.game.domain.enums.Move;
import com.segundo_desafio.game.persistence.repository.MatchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchHistoryService {

    @Autowired
    private MatchHistoryRepository repository;


    public List<MatchHistoryResponseDTO> getAllRounds(){
        return repository.findAll().stream()
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
