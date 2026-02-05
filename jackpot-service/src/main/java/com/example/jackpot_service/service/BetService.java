package com.example.jackpot_service.service;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jackpot_service.dto.bet.BetResultResponse;
import com.example.jackpot_service.model.Bet;
import com.example.jackpot_service.model.Jackpot;
import com.example.jackpot_service.model.Win;
import com.example.jackpot_service.repository.BetRepository;
import com.example.jackpot_service.repository.JackpotRepository;
import com.example.jackpot_service.repository.WinRepository;

@Service
@Transactional
public class BetService {

    private final BetRepository betRepository;
    private final JackpotRepository jackpotRepository;
    private final WinRepository winRepository;

    public BetService(BetRepository betRepository, JackpotRepository jackpotRepository, WinRepository winRepository) {
        this.betRepository = betRepository;
        this.jackpotRepository = jackpotRepository;
        this.winRepository = winRepository;
    }

    public BetResultResponse placeBet(Long jackpotId, String playerAlias, BigDecimal betAmount) {

        Jackpot jackpot = jackpotRepository.findById(jackpotId)
                .orElseThrow(() -> new IllegalArgumentException("Jackpot does not exist."));

        // 1. Saving the bet
        Bet bet = new Bet();
        bet.setJackpot(jackpot);
        bet.setPlayerAlias(playerAlias);
        bet.setBetAmount(betAmount);
        bet.setTimestamp(Instant.now());
        betRepository.save(bet);

        // 2. Adding bet amount to jackpot
        jackpot.setCurrentSize(jackpot.getCurrentSize().add(betAmount));

        BigDecimal winAmount = BigDecimal.ZERO;

        // 3. Determining win or loss
        if (Math.random() < jackpot.getWinProbability()) {

            // Player wins
            winAmount = jackpot.getCurrentSize();

            // Creating Win record
            Win win = new Win();
            win.setJackpot(jackpot);
            win.setPlayerAlias(playerAlias);
            win.setAmount(winAmount);
            win.setTimestamp(Instant.now());
            winRepository.save(win);

            // Resetting jackpot
            jackpot.setCurrentSize(BigDecimal.ZERO);
            jackpot.setWinsCount(jackpot.getWinsCount() + 1);
            jackpot.setLastWinTimestamp(Instant.now());
        }

        jackpotRepository.save(jackpot);

        return new BetResultResponse(
                winAmount,
                jackpot.getCurrentSize());
    }

}
