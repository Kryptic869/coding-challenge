package com.example.jackpot_service.dto.bet;

import java.math.BigDecimal;

public record BetResultResponse(
                BigDecimal winAmount,
                BigDecimal newJackpotSize) {

}
