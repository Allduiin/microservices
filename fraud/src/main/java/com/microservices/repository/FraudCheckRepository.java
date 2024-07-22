package com.microservices.repository;

import com.microservices.model.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudCheckRepository extends JpaRepository<FraudCheckHistory, Integer> {
}
