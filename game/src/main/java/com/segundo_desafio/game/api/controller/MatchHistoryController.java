package com.segundo_desafio.game.api.controller;

import com.segundo_desafio.game.domain.dto.MatchHistoryResponseDTO;
import com.segundo_desafio.game.domain.service.MatchHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class MatchHistoryController {


    private final MatchHistoryService matchHistoryService;

    public MatchHistoryController(MatchHistoryService service){
        this.matchHistoryService = service;
    }

    @GetMapping("/history")
    public ResponseEntity<List<MatchHistoryResponseDTO>> getAll(){
        return ResponseEntity.ok(matchHistoryService.getAllRounds());
    }
}
