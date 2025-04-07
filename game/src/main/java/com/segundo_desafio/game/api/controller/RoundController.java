package com.segundo_desafio.game.api.controller;


import com.segundo_desafio.game.domain.dto.RoundRequestDTO;
import com.segundo_desafio.game.domain.dto.RoundResponseDTO;
import com.segundo_desafio.game.domain.service.RoundService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class RoundController {

    @Autowired
    private RoundService roundService;

    @PostMapping("/play")
    public ResponseEntity<RoundResponseDTO> startGame(@RequestBody RoundRequestDTO body){

        roundService.saveRound(body);
        RoundResponseDTO res = roundService.startGame(body);

        return ResponseEntity.ok(res);
    }

}
