package com.segundo_desafio.game.domain.service;

import com.segundo_desafio.game.api.model.exception.InvalidGameRequestException;
import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import com.segundo_desafio.game.domain.dto.RoundResponseDTO;
import com.segundo_desafio.game.domain.validator.MoveValidator;
import com.segundo_desafio.game.domain.validator.RoundRequestValidator;
import com.segundo_desafio.game.persistence.model.MatchHistory;
import com.segundo_desafio.game.persistence.repository.MatchHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RoundServiceTest {

    @Mock
    private MatchHistoryRepository repository;

    @Mock
    private MoveValidator moveValidator;

    @Mock
    private RoundRequestValidator roundRequestValidator;

    @InjectMocks
    private RoundService service;

    @Test
    void shouldSaveRoundSuccessfully (){

        RoundRequestDTO dto = new RoundRequestDTO("ROCK", "Lucca", "PAPER", "Bot");

        doNothing().when(roundRequestValidator).validate(dto);
        doNothing().when(moveValidator).validate(dto);

        service.saveRound(dto);

        ArgumentCaptor<MatchHistory> captor = ArgumentCaptor.forClass(MatchHistory.class);
        verify(repository).save(captor.capture());

        assertEquals("ROCK", captor.getValue().getPlayerOneMove());
        assertEquals("Lucca", captor.getValue().getPlayerOneName());
        assertEquals("PAPER", captor.getValue().getPlayerTwoMove());
        assertEquals("Bot", captor.getValue().getPlayerTwoName());
        assertNotNull(captor.getValue().getDate());

    }
    @Test
    void shouldFailWhenTryToSave (){
        RoundRequestDTO dto = new RoundRequestDTO("ROCK", "Lucca", "PAPER", "Bot");
        doNothing().when(roundRequestValidator).validate(dto);
        doThrow(new InvalidGameRequestException("invalid")).when(moveValidator).validate(dto);

        assertThrows(InvalidGameRequestException.class, () -> {
            service.saveRound(dto);
        });

    }


    @ParameterizedTest
    @CsvSource({
            "ROCK, SCISSORS, PLAYER_ONE_WINS",
            "ROCK, PAPER, PLAYER_TWO_WINS",
            "PAPER, PAPER, DRAW"
    })
    void shouldReturnCorrectWinner(String p1Move, String p2Move, String expectedWinner){
        RoundRequestDTO dto = new RoundRequestDTO(p1Move, "Lucca", p2Move, "Bot");

        RoundResponseDTO response = service.saveRound(dto);

        assertEquals(expectedWinner, response.result());
        assertEquals(p1Move, response.playerOneMove());
        assertEquals("Lucca", response.playerOneName());
        assertEquals(p2Move, response.playerTwoMove());
        assertEquals("Bot", response.playerTwoName());

    }

}