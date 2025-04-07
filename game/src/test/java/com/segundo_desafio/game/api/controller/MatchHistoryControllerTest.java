package com.segundo_desafio.game.api.controller;

import com.segundo_desafio.game.domain.dto.MatchHistoryResponseDTO;
import com.segundo_desafio.game.domain.service.MatchHistoryService;
import com.segundo_desafio.game.persistence.model.MatchHistory;
import com.segundo_desafio.game.persistence.repository.MatchHistoryRepository;
import net.bytebuddy.asm.Advice;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MatchHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    MatchHistoryService matchHistoryService;

    @Mock
    private MatchHistoryRepository matchHistoryRepository;

    @Test
    void shouldReturnHistoryWhenMatchHistoryExists() throws Exception {

        MatchHistory matchHistory = new MatchHistory();
        matchHistory.setPlayerOneMove("ROCK");
        matchHistory.setPlayerTwoMove("PAPER");
        matchHistory.setPlayerOneName("player1");
        matchHistory.setPlayerTwoName("player2");

        MatchHistoryResponseDTO match1 = new MatchHistoryResponseDTO(
                "ROCK", "player1", "PAPER", "player2", LocalDateTime.now(), "PLAYER_TWO_WINS");

        List<MatchHistoryResponseDTO> matchHistoryList = List.of(match1);

        when(matchHistoryRepository.findAll()).thenReturn(List.of(new MatchHistory()));
        when(matchHistoryService.getAllRounds()).thenReturn(matchHistoryList);

        mockMvc.perform(get("/api/game/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerOneMove").value("ROCK"))
                .andExpect(jsonPath("$[0].playerTwoMove").value("PAPER"))
                .andExpect(jsonPath("$[0].result").value("DRAW"));
    }
}