package com.microservices.service;

import com.microservices.clients.notification.NotificationRequest;
import com.microservices.model.Notification;
import com.microservices.repository.NotificationRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public record NotificationService(NotificationRepository repository) {
    public void save(NotificationRequest request) {
        Notification notification = Notification.builder()
            .customerId(request.customerId())
            .createdAt(LocalDateTime.now())
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .phone(request.phone())
            .build();
        repository.save(notification);
    }
}
