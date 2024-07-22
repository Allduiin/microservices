package com.microservices.service;

import com.microservices.api.RegisterCustomerRequest;
import com.microservices.model.Customer;
import com.microservices.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public Customer register(RegisterCustomerRequest request) {
        Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .phone(request.phone())
            .build();
        // todo: validate request
        // todo: check unique
        return customerRepository.save(customer);
    }
}
