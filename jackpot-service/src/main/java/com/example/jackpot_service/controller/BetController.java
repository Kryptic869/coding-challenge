package com.example.jackpot_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jackpot_service.dto.bet.BetResultResponse;
import com.example.jackpot_service.dto.bet.CreateBetRequest;
import com.example.jackpot_service.service.BetService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/bets")
public class BetController {
    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping
    public ResponseEntity<BetResultResponse> placeBet(
            @RequestBody CreateBetRequest request) {

        BetResultResponse response = betService.placeBet(
                request.jackpotId(),
                request.playerAlias(),
                request.betAmount());

        return ResponseEntity.ok(response);
    }

}
