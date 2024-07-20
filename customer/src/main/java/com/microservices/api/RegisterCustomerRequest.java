package com.microservices.api;

public record RegisterCustomerRequest(
    String firstName,
    String lastName,
    String email,
    String phone
) {}
