package com.example.jackpot_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jackpot_service.model.Jackpot;

public interface JackpotRepository extends JpaRepository<Jackpot, Long> {
    boolean existsByName(String name);

}
