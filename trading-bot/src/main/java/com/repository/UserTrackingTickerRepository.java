package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.UserTrackingTicker;

@Repository
public interface UserTrackingTickerRepository extends JpaRepository<UserTrackingTicker, Integer> {
}

