package com.example.jackpot_service.service;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Pageable;
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
    public List<Win> getWins(int limit, Instant from, Instant to) {
        Pageable pageable = PageRequest.of(0, limit);

        if (from != null && to != null) {
            return winRepository
                    .findByTimestampBetweenOrderByTimestampDesc(
                            from,
                            to,
                            pageable)
                    .getContent();

        }

        if (from != null) {
            return winRepository
                    .findByTimestampGreaterThanEqualOrderByTimestampDesc(
                            from,
                            pageable)
                    .getContent();
        }

        if (to != null) {
            return winRepository
                    .findByTimestampLessThanEqualOrderByTimestampDesc(
                            to,
                            pageable)
                    .getContent();
        }

        return winRepository.findAllByOrderByTimestampDesc(pageable)
                .getContent();
    }
}
