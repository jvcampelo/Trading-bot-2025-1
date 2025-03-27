package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.UserConfiguration;

@Repository
public interface UserConfigurationRepository extends JpaRepository<UserConfiguration, Integer> {
}
