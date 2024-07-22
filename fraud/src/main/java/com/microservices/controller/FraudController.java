package com.microservices.controller;

import com.microservices.api.FraudCheckResponse;
import com.microservices.service.FraudCheckService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public record FraudController(FraudCheckService fraudCheckService) {

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        boolean isFraudster = fraudCheckService.checkCustomer(customerId);
        return new FraudCheckResponse(isFraudster);
    }
}
