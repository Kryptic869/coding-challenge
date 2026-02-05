package com.example.jackpot_service.dto.win;

import java.math.BigDecimal;
import java.time.Instant;

public record WinListResponse(
                Instant timestamp,
                String playerAlias,
                BigDecimal winAmount) {

}
