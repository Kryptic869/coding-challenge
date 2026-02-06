package com.example.jackpot_service.dto.jackpot;

import java.math.BigDecimal;
import java.time.Instant;

public record JackpotListResponse(
                Long id,
                BigDecimal currentSize,
                int winsCount,
                Instant lastWinTimestamp) {

}
