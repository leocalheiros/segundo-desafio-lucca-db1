package com.segundo_desafio.game.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.segundo_desafio.game.domain.dto.MatchHistoryResponseDTO;
import com.segundo_desafio.game.domain.service.MatchHistoryService;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;



@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MatchHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MatchHistoryService matchHistoryService;


    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldReturnHistoryWhenMatchHistoryExists() throws Exception {
        MatchHistoryResponseDTO match1 = new MatchHistoryResponseDTO(
            "ROCK", "player1", "PAPER", "player2", LocalDateTime.now(), "PLAYER_TWO_WINS");

        List<MatchHistoryResponseDTO> expectedList = List.of(match1);

        given(matchHistoryService.getAllRounds()).willReturn(expectedList);


        mockMvc.perform(get("/api/game/history")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerOneMove").value("ROCK"))
                .andExpect(jsonPath("$[0].playerTwoMove").value("PAPER"))
                .andExpect(jsonPath("$[0].result").value("PLAYER_TWO_WINS"));
    }

    @Test
    void shouldReturnEmptyHistoryWhenMatchHistoryDoesNotExist() throws Exception{

        given(matchHistoryService.getAllRounds()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/api/game/history")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}