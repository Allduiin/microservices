package com.microservices.service;

import com.microservices.api.RegisterCustomerRequest;
import com.microservices.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public boolean register(RegisterCustomerRequest request) {
        Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .phone(request.phone())
            .build();
        // todo: validate request
        // todo: check unique
        // todo: add to db
        return true;
    }
}
