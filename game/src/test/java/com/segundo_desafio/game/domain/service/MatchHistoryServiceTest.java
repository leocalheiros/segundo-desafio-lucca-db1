package com.segundo_desafio.game.domain.service;

import com.segundo_desafio.game.domain.dto.MatchHistoryResponseDTO;
import com.segundo_desafio.game.persistence.model.MatchHistory;
import com.segundo_desafio.game.persistence.repository.MatchHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MatchHistoryServiceTest {

    @Mock
    private MatchHistoryRepository repository;

    @InjectMocks
    MatchHistoryService service;


    @Test
    void shoulReturnEmptyList(){

        when(repository.findAll()).thenReturn(List.of());

        List<MatchHistoryResponseDTO> result = service.getAllRounds();

        assertNotNull(result);
        assertTrue(result.isEmpty());

    }

    @Test
    void shouldReturnAListWithCorrectWinner(){

        MatchHistory history1 = new MatchHistory();
        history1.setPlayerOneMove("ROCK");
        history1.setPlayerOneName("Lucca");
        history1.setPlayerTwoMove("SCISSORS");
        history1.setPlayerTwoName("Bot");
        history1.setDate(LocalDateTime.now());

        MatchHistory history2 = new MatchHistory();
        history2.setPlayerOneMove("SCISSORS");
        history2.setPlayerOneName("teste");
        history2.setPlayerTwoMove("SCISSORS");
        history2.setPlayerTwoName("Bot");
        history2.setDate(LocalDateTime.now());

        List<MatchHistory> fakeHistory = List.of(history1,history2);

        when(repository.findAll()).thenReturn(fakeHistory);

        List<MatchHistoryResponseDTO> result = service.getAllRounds();


        assertEquals(2,result.size());

        MatchHistoryResponseDTO dto1 = result.getFirst();
        assertEquals("PLAYER_ONE_WINS", dto1.result());
        assertEquals("ROCK", dto1.playerOneMove());
        assertEquals("SCISSORS", dto1.playerTwoMove());

        MatchHistoryResponseDTO dto2 = result.get(1);
        assertEquals("DRAW", dto2.result());
        assertEquals("SCISSORS", dto2.playerOneMove());
        assertEquals("SCISSORS", dto2.playerTwoMove());





    }




}