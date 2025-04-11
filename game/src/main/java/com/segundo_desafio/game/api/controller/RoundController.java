package com.segundo_desafio.game.api.controller;


import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import com.segundo_desafio.game.domain.dto.RoundResponseDTO;
import com.segundo_desafio.game.domain.service.MatchHistoryService;
import com.segundo_desafio.game.domain.service.RoundService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/play")
@RequiredArgsConstructor
public class RoundController {

    private final RoundService roundService;
    private final Logger logger = LogManager.getLogger(MatchHistoryService.class.getName());

    @PostMapping
    public ResponseEntity<RoundResponseDTO> startGame(@RequestBody RoundRequestDTO body){

        RoundResponseDTO res = roundService.saveRound(body);
        logger.info(res);

        return ResponseEntity.ok(res);
    }

}
