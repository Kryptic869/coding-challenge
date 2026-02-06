package com.example.jackpot_service.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.jackpot_service.model.Win;
import com.example.jackpot_service.repository.WinRepository;

@Service
public class WinService {
    private final WinRepository winRepository;

    public WinService(WinRepository winRepository) {
        this.winRepository = winRepository;
    }

    // Getting recent wins:
    public List<Win> getWins(int limit) {
        return winRepository
                .findAllByOrderByTimestampDesc(
                        PageRequest.of(0, limit))
                .getContent();
    }
}
