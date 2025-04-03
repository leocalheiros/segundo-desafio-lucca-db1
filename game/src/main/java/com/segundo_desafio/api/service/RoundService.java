package com.segundo_desafio.api.service;

import com.segundo_desafio.api.domain.gamerules.GameRules;
import com.segundo_desafio.api.domain.round.RoundResponseDTO;
import com.segundo_desafio.api.model.Move;
import com.segundo_desafio.api.domain.matchhistory.MatchHistory;
import com.segundo_desafio.api.domain.round.RoundRequestDTO;
import com.segundo_desafio.api.exception.InvalidGameRequestException;
import com.segundo_desafio.api.repository.MatchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RoundService {

    @Autowired
    private MatchHistoryRepository repository;

    private void roundRequestValidation(RoundRequestDTO round){
        if(round.playerOneName() == null || round.playerOneName().isEmpty()){
            throw new InvalidGameRequestException("Player one name cannot be null or empty");
        }
        if(round.playerTwoName() == null || round.playerTwoName().isEmpty()){
            throw new InvalidGameRequestException("Player two name cannot be null or empty");
        }
        if(round.playerOneMove() == null || isInvalidMove(round.playerOneMove())){
            throw new InvalidGameRequestException("Invalid move for player one. Must be rock, paper, or scissors.");
        }
        if(round.playerTwoMove() == null || isInvalidMove(round.playerTwoMove())){
            throw new InvalidGameRequestException("Invalid move for player two. Must be rock, paper, or scissors.");
        }
    }
    private boolean isInvalidMove(String newMove){
        try {
            Move.valueOf(newMove.toUpperCase());
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }


    public void saveRound (RoundRequestDTO data){
        roundRequestValidation(data);

        MatchHistory history = new MatchHistory();
        history.setPlayerOneMove(data.playerOneMove().toUpperCase());
        history.setPlayerOneName(data.playerOneName());
        history.setPlayerTwoMove(data.playerTwoMove().toUpperCase());
        history.setPlayerTwoName(data.playerTwoName());
        history.setDate(LocalDateTime.now());

        repository.save(history);
    }

    public RoundResponseDTO getWinner(RoundRequestDTO data){

        Move playerOneMove = Move.valueOf(data.playerOneMove().toUpperCase());
        Move playerTwoMove = Move.valueOf(data.playerTwoMove().toUpperCase());

        String winner = GameRules.checkWinner(playerOneMove, playerTwoMove);

        return new RoundResponseDTO(
                data.playerOneMove().toUpperCase(),
                data.playerOneName(),
                data.playerTwoMove().toUpperCase(),
                data.playerTwoName(),
                winner);
    }
}
