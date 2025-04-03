package com.segundo_desafio.api.service;


import com.segundo_desafio.api.domain.gamerules.GameRules;
import com.segundo_desafio.api.domain.matchhistory.MatchHistory;
import com.segundo_desafio.api.domain.matchhistory.MatchHistoryResponseDTO;
import com.segundo_desafio.api.model.Move;
import com.segundo_desafio.api.repository.MatchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        String result = GameRules.checkWinner(playerOneMove, playerTwoMove);

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
