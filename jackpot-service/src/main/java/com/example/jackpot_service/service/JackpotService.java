package com.example.jackpot_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jackpot_service.model.Jackpot;
import com.example.jackpot_service.repository.JackpotRepository;

@Service
public class JackpotService {

    private final JackpotRepository jackpotRepository;

    public JackpotService(JackpotRepository jackpotRepository) {
        this.jackpotRepository = jackpotRepository;
    }

    // Creating a jackpot flow:
    public Jackpot createJackpot(String name, double winProbability) {

        // 1. Validating the jackpot name is unique
        if (jackpotRepository.existsByName(name)) {
            throw new IllegalArgumentException("Jackpot with the same name already exists.");
        }

        // 2. Validating win probability is between 0 and 1
        if (winProbability < 0 || winProbability > 1) {
            throw new IllegalArgumentException("Win probability must be between 0 and 1.");
        }

        // 3. Creating and saving the jackpot
        Jackpot jackpot = new Jackpot();
        jackpot.setName(name);
        jackpot.setWinProbability(winProbability);

        return jackpotRepository.save(jackpot);
    }

    // Getting all jackpots:
    public List<Jackpot> getAllJackpots() {
        return jackpotRepository.findAll();
    }

}
