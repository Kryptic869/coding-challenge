package com.example.jackpot_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jackpot_service.model.Win;

public interface WinRepository extends JpaRepository<Win, Long> {

    // Method to find all wins ordered by timestamp in descending order with
    // pagination
    // Pagination is used to limit the number of results returned (Providing
    // limiting)
    Page<Win> findAllByOrderByTimestampDesc(Pageable pageable);
}
