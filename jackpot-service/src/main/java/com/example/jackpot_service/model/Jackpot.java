package com.example.jackpot_service.model;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jackpots")
public class Jackpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private double winProbability;

    @Column(nullable = false)
    private BigDecimal currentSize = BigDecimal.ZERO;

    @Column(nullable = false)
    private int winsCount = 0;

    private Instant lastWinTimestamp;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWinProbability() {
        return winProbability;
    }

    public BigDecimal getCurrentSize() {
        return currentSize;
    }

    public int getWinsCount() {
        return winsCount;
    }

    public Instant getLastWinTimestamp() {
        return lastWinTimestamp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWinProbability(double winProbability) {
        this.winProbability = winProbability;
    }

    public void setCurrentSize(BigDecimal currentSize) {
        this.currentSize = currentSize;
    }

    public void setWinsCount(int winsCount) {
        this.winsCount = winsCount;
    }

    public void setLastWinTimestamp(Instant lastWinTimestamp) {
        this.lastWinTimestamp = lastWinTimestamp;
    }
}
