package com.example.jackpot_service.repository;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jackpot_service.model.Win;

public interface WinRepository extends JpaRepository<Win, Long> {

    // Method to find all wins ordered by timestamp in descending order with
    // pagination, alongside filtering capabilities (to and from).
    // Pagination is used to limit the number of results returned (Providing
    // limiting)
    Page<Win> findAllByOrderByTimestampDesc(Pageable pageable);

    Page<Win> findByTimestampGreaterThanEqualOrderByTimestampDesc(
            Instant from,
            Pageable pageable);

    Page<Win> findByTimestampLessThanEqualOrderByTimestampDesc(
            Instant to,
            Pageable pageable);

    Page<Win> findByTimestampBetweenOrderByTimestampDesc(
            Instant from,
            Instant to,
            Pageable pageable);
}
