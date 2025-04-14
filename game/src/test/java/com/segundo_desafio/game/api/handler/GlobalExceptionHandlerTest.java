package com.segundo_desafio.game.api.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class GlobalExceptionHandlerTest {



    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnBadRequestWhenPlayerOneNameIsNull() throws Exception {

        RoundRequestDTO requestDTO = new RoundRequestDTO("rock", null, "PAPER", "dale");

        mockMvc.perform(post("/api/game/play")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Request"))
                .andExpect(jsonPath("$.message").value("Player one name cannot be null or empty"));

    }

    @Test
    void shouldReturnBadRequestWhenPlayerOneNameIsEmpty() throws Exception {

        RoundRequestDTO requestDTO = new RoundRequestDTO("rock", "", "PAPER", "dale");

        mockMvc.perform(post("/api/game/play")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Request"))
                .andExpect(jsonPath("$.message").value("Player one name cannot be null or empty"));

    }

    @Test
    void shouldReturnBadRequestWhenPlayerTwoNameIsNull() throws Exception {

        RoundRequestDTO requestDTO = new RoundRequestDTO("rock", "teste", "PAPER", null);

        mockMvc.perform(post("/api/game/play")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Request"))
                .andExpect(jsonPath("$.message").value("Player two name cannot be null or empty"));

    }

    @Test
    void shouldReturnBadRequestWhenPlayerTwoNameIsEmpty() throws Exception {

        RoundRequestDTO requestDTO = new RoundRequestDTO("rock", "One", "PAPER", "");

        mockMvc.perform(post("/api/game/play")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Request"))
                .andExpect(jsonPath("$.message").value("Player two name cannot be null or empty"));

    }

    @Test
    void shouldReturnBadRequestWhenPlayerOneMoveIsNull() throws Exception {

        RoundRequestDTO requestDTO = new RoundRequestDTO(null, "One", "PAPER", "teste");

        mockMvc.perform(post("/api/game/play")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Request"))
                .andExpect(jsonPath("$.message").value("Player one move is required"));

    }

    @Test
    void shouldReturnBadRequestWhenPlayerOneMoveIEmpty() throws Exception {

        RoundRequestDTO requestDTO = new RoundRequestDTO("", "One", "PAPER", "teste");

        mockMvc.perform(post("/api/game/play")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Request"))
                .andExpect(jsonPath("$.message").value("Player one move is required"));

    }

    @Test
    void shouldReturnBadRequestWhenPlayerTwoMoveIsNull() throws Exception {

        RoundRequestDTO requestDTO = new RoundRequestDTO("Rock", "One", null, "teste");

        mockMvc.perform(post("/api/game/play")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Request"))
                .andExpect(jsonPath("$.message").value("Player two move is required"));

    }

    @Test
    void shouldReturnBadRequestWhenPlayerTwoMoveIsEmpty() throws Exception {

        RoundRequestDTO requestDTO = new RoundRequestDTO("Rock", "One", "", "teste");

        mockMvc.perform(post("/api/game/play")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Request"))
                .andExpect(jsonPath("$.message").value("Player two move is required"));

    }

    @Test
    void shouldReturnBadRequestWhenInvalidMoveIsProvided() throws Exception {

        RoundRequestDTO requestDTO = new RoundRequestDTO("INVALID_MOVE", "vrau", "PAPER", "dale");


        mockMvc.perform(post("/api/game/play")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Request"))
                .andExpect(jsonPath("$.message").value("One or both moves are invalid (must be ROCK, PAPER or SCISSORS)"));
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