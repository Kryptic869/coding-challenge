package com.example.jackpot_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jackpot_service.model.Bet;

public interface BetRepository extends JpaRepository<Bet, Long> {

}
