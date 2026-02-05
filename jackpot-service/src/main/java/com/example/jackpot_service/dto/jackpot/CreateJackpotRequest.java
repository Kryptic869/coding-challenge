package com.example.jackpot_service.dto.jackpot;

public record CreateJackpotRequest(
        String name,
        double winProbability) {

}