package com.example.jackpot_service.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.example.jackpot_service.model.Jackpot;
import com.example.jackpot_service.model.Win;
import com.example.jackpot_service.repository.JackpotRepository;
import com.example.jackpot_service.repository.WinRepository;

@DataJpaTest
public class WinServiceTest {

    @Autowired
    private WinRepository winRepository;
    private WinService winService;

    @Autowired
    private JackpotRepository jackpotRepository;
    private Jackpot jackpot;

    @BeforeEach
    void setUp() {
        winService = new WinService(winRepository);

        jackpot = new Jackpot();
        jackpot.setName("Test Jackpot");
        jackpot.setWinProbability(0.5);
        jackpot.setCurrentSize(BigDecimal.ZERO);
        jackpot.setWinsCount(0);
        jackpot = jackpotRepository.save(jackpot);

        Win win1 = new Win();
        win1.setJackpot(jackpot);
        win1.setPlayerAlias("Player1");
        win1.setAmount(BigDecimal.valueOf(100));
        win1.setTimestamp(Instant.parse("2026-02-01T10:00:00Z"));
        winRepository.save(win1);

        Win win2 = new Win();
        win2.setJackpot(jackpot);
        win2.setPlayerAlias("Player2");
        win2.setAmount(BigDecimal.valueOf(200));
        win2.setTimestamp(Instant.parse("2026-02-05T10:00:00Z"));
        winRepository.save(win2);
    }

    @Test
    void testReturnAllWinsWhenNoFilters() {
        List<Win> wins = winService.getWins(10, null, null);
        assertThat(wins).hasSize(2);
    }

    @Test
    void testFilterWinsFromTimestamp() {
        Instant from = Instant.parse("2026-02-03T00:00:00Z");

        List<Win> wins = winService.getWins(10, from, null);

        assertThat(wins).hasSize(1);
        assertThat(wins.get(0).getPlayerAlias()).isEqualTo("Player2");
    }

    @Test
    void testFilterWinsBetweenTimestamps() {
        Instant from = Instant.parse("2026-02-01T00:00:00Z");
        Instant to = Instant.parse("2026-02-02T23:59:59Z");

        List<Win> wins = winService.getWins(10, from, to);

        assertThat(wins).hasSize(1);
        assertThat(wins.get(0).getPlayerAlias()).isEqualTo("Player1");
    }
}
