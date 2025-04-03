package com.segundo_desafio.api.controller;

import com.segundo_desafio.api.domain.matchhistory.MatchHistoryResponseDTO;
import com.segundo_desafio.api.service.MatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class MatchHistoryController {

    @Autowired
    private MatchHistoryService matchHistoryService;

    @GetMapping("/history")
    public ResponseEntity<List<MatchHistoryResponseDTO>> getAll(){
        return ResponseEntity.ok(matchHistoryService.getAllRounds());
    }
}
