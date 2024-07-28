package com.microservices.clients.notification;

public record NotificationRequest (
    Integer customerId,
    String firstName,
    String lastName,
    String email,
    String phone
) {}
