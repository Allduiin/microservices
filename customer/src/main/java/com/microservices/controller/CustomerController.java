package com.microservices.controller;

import com.microservices.api.RegisterCustomerRequest;
import com.microservices.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public void register(@RequestBody RegisterCustomerRequest registrationRequest) {
        log.info("Customer registration started {}", registrationRequest);
        customerService.register(registrationRequest);
    }
}
