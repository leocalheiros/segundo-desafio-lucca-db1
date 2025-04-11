package com.segundo_desafio.game.api.controller;

import com.segundo_desafio.game.domain.dto.MatchHistoryResponseDTO;
import com.segundo_desafio.game.domain.service.MatchHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class MatchHistoryController {


    private final MatchHistoryService matchHistoryService;
    private final Logger logger = LogManager.getLogger(MatchHistoryService.class.getName());



    @GetMapping
    public ResponseEntity<List<MatchHistoryResponseDTO>> getAll(){

        List<MatchHistoryResponseDTO> res = matchHistoryService.getAllRounds();
        logger.info(res);

        return ResponseEntity.ok(res);
    }
}
