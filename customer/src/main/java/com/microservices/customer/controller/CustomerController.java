package com.microservices.customer.controller;

import com.microservices.customer.api.RegisterCustomerRequest;
import com.microservices.customer.model.Customer;
import com.microservices.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public Customer register(@RequestBody RegisterCustomerRequest registrationRequest) {
        log.info("Customer registration started {}", registrationRequest);
        return customerService.register(registrationRequest);
    }
}
