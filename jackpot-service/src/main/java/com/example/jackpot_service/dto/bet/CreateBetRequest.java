package com.example.jackpot_service.dto.bet;

import java.math.BigDecimal;

public record CreateBetRequest(
        Long jackpotId,
        String playerAlias,
        BigDecimal betAmount) {

}
