package com.microservices.customer.api;

public record RegisterCustomerRequest(
    String firstName,
    String lastName,
    String email,
    String phone
) {}
