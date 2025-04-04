package com.repository;
import com.model.UserOrderReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderReportRepository extends JpaRepository<UserOrderReport, Integer> {
}