package com.example.jackpot_service.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.example.jackpot_service.dto.bet.BetResultResponse;
import com.example.jackpot_service.model.Jackpot;
import com.example.jackpot_service.repository.BetRepository;
import com.example.jackpot_service.repository.JackpotRepository;
import com.example.jackpot_service.repository.WinRepository;

@DataJpaTest
public class BetServiceTest {

    @Autowired
    private BetRepository betRepository;
    private BetService betService;

    @Autowired
    private JackpotRepository jackpotRepository;
    private Jackpot jackpot;

    @Autowired
    private WinRepository winRepository;

    @BeforeEach
    void setUp() {
        betService = new BetService(betRepository, jackpotRepository, winRepository);

        jackpot = new Jackpot();
        jackpot.setName("Test Jackpot");
        jackpot.setWinProbability(0.5);
        jackpot.setCurrentSize(BigDecimal.ZERO);
        jackpot.setWinsCount(0);

        jackpot = jackpotRepository.save(jackpot);
    }

    // Test case for placing a bet and losing
    @Test
    void testPlaceBet_Loss_AndIncreaseJackpotSize() {
        // Mocking Math.random() to always return a value greater than the win
        // probability to simulate a loss
        jackpot.setWinProbability(0.0); // Setting win probability to 0 to ensure loss
        jackpotRepository.save(jackpot);

        BigDecimal betAmount = BigDecimal.valueOf(70);

        BetResultResponse result = betService.placeBet(
                jackpot.getId(),
                "Player1",
                betAmount);

        Jackpot updatedJackpot = jackpotRepository.findById(jackpot.getId()).orElseThrow();

        assertThat(result.winAmount()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(updatedJackpot.getCurrentSize()).isEqualByComparingTo(betAmount);
        assertThat(updatedJackpot.getWinsCount()).isEqualTo(0);
    }

    // Test case for placing a bet and winning
    @Test
    void testPlaceBet_Win_AndResetJackpotSize() {
        // Mocking Math.random() to always return a value less than the win probability
        // to simulate a win
        jackpot.setWinProbability(1.0); // Setting win probability to 1 to ensure win
        jackpotRepository.save(jackpot);

        BigDecimal betAmount = BigDecimal.valueOf(50);

        BetResultResponse result = betService.placeBet(
                jackpot.getId(),
                "Player1",
                betAmount);

        Jackpot updatedJackpot = jackpotRepository.findById(jackpot.getId()).orElseThrow();

        // Asserting response
        assertThat(result.winAmount()).isEqualByComparingTo(betAmount);
        assertThat(result.newJackpotSize()).isEqualByComparingTo(BigDecimal.ZERO);

        // Asserting jackpot state
        assertThat(updatedJackpot.getCurrentSize()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(updatedJackpot.getWinsCount()).isEqualTo(1);
        assertThat(updatedJackpot.getLastWinTimestamp()).isNotNull();

        // Asserting win record updated
        assertThat(winRepository.findAll()).hasSize(1);
    }
}
