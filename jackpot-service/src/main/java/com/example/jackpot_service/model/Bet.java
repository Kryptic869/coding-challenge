package com.example.jackpot_service.model;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "jackpot_id")
    private Jackpot jackpot;

    @Column(nullable = false)
    private String playerAlias;

    @Column(nullable = false)
    private BigDecimal betAmount;

    private Instant timestamp;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Jackpot getJackpot() {
        return jackpot;
    }

    public String getPlayerAlias() {
        return playerAlias;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setJackpot(Jackpot jackpot) {
        this.jackpot = jackpot;
    }

    public void setPlayerAlias(String playerAlias) {
        this.playerAlias = playerAlias;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}