package com.example.jackpot_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jackpot_service.dto.jackpot.CreateJackpotRequest;
import com.example.jackpot_service.dto.jackpot.JackpotListResponse;
import com.example.jackpot_service.dto.jackpot.CreateJackpotResponse;
import com.example.jackpot_service.model.Jackpot;
import com.example.jackpot_service.service.JackpotService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/jackpots")
public class JackpotController {
        private final JackpotService jackpotService;

        public JackpotController(JackpotService jackpotService) {
                this.jackpotService = jackpotService;
        }

        @PostMapping
        public ResponseEntity<CreateJackpotResponse> createJackpot(
                        @RequestBody CreateJackpotRequest request) {
                Jackpot jackpot = jackpotService.createJackpot(
                                request.name(),
                                request.winProbability());

                CreateJackpotResponse response = new CreateJackpotResponse(
                                jackpot.getId(),
                                jackpot.getName(),
                                jackpot.getCurrentSize(),
                                jackpot.getWinsCount(),
                                jackpot.getLastWinTimestamp());

                return ResponseEntity.ok(response);
        }

        @GetMapping
        public List<JackpotListResponse> getJackpots() {
                return jackpotService.getAllJackpots()
                                .stream()
                                .map(jackpot -> new JackpotListResponse(
                                                jackpot.getId(),
                                                jackpot.getCurrentSize(),
                                                jackpot.getWinsCount(),
                                                jackpot.getLastWinTimestamp()))
                                .toList();
        }
}
