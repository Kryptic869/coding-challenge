package com.example.jackpot_service.dto.jackpot;

import java.math.BigDecimal;
import java.time.Instant;

public record CreateJackpotResponse(
        Long id,
        String name,
        BigDecimal currentSize,
        int winsCount,
        Instant lastWinTimestamp) {

}
