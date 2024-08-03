package com.microservices.notification.service;

import com.microservices.notification.model.FraudCheckHistory;
import com.microservices.notification.repository.FraudCheckRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public record FraudCheckService(FraudCheckRepository fraudCheckRepository) {
    public boolean checkCustomer(Integer customerId) {
        fraudCheckRepository.save(
            FraudCheckHistory.builder()
                .customerId(customerId)
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build()
        );
        return false;
    }
}
