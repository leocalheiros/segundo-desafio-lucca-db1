package com.segundo_desafio.game.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RoundControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private MatchHistoryController matchHistoryController;

    @Test
    void shouldReturnBadRequestWhenPlayerOneMoveIsNull() throws Exception{

        RoundRequestDTO requestDTO = new RoundRequestDTO(null, "vrau", "PAPER", "dale");

        mockMvc.perform(post("/api/game/play")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Request"))
                .andExpect(jsonPath("$.message").value("Player one move is required"));
    }

    @Test
    void ShouldReturnOkWhenValidDtoIsProvided() throws Exception {

        RoundRequestDTO requestDTO = new RoundRequestDTO("ROCK", "vrau", "PAPER", "dale");


        mockMvc.perform(post("/api/game/play")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("PLAYER_TWO_WINS"))
                .andExpect(jsonPath("$.playerOneMove").value("ROCK"))
                .andExpect(jsonPath("$.playerTwoMove").value("PAPER"))
                .andExpect(jsonPath("$.playerOneName").value("vrau"))
                .andExpect(jsonPath("$.playerTwoName").value("dale"));
    }

}