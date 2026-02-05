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

    public Jackpot createJackpot(String name, double winProbability) {
        if (jackpotRepository.existsByName(name)) {
            throw new IllegalArgumentException("Jackpot with the same name already exists.");
        }

        Jackpot jackpot = new Jackpot();
        jackpot.setName(name);
        jackpot.setWinProbability(winProbability);

        return jackpotRepository.save(jackpot);
    }

    public List<Jackpot> getAllJackpots() {
        return jackpotRepository.findAll();
    }

}
