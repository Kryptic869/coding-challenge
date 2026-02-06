package com.example.jackpot_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jackpot_service.dto.win.WinListResponse;
import com.example.jackpot_service.service.WinService;

import java.time.Instant;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/wins")
public class WinController {
    private final WinService winService;

    public WinController(WinService winService) {
        this.winService = winService;
    }

    @GetMapping
    public List<WinListResponse> getWins(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) Instant from,
            @RequestParam(required = false) Instant to) {
        return winService.getWins(limit, from, to)
                .stream()
                .map(win -> new WinListResponse(
                        win.getTimestamp(),
                        win.getPlayerAlias(),
                        win.getAmount()))
                .toList();
    }
}
