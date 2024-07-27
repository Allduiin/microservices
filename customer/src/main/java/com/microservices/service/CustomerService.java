package com.microservices.service;

import com.microservices.api.RegisterCustomerRequest;
import com.microservices.clients.fraud.FraudCheckResponse;
import com.microservices.clients.fraud.FraudClient;
import com.microservices.model.Customer;
import com.microservices.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
    CustomerRepository customerRepository,
    RestTemplate restTemplate,
    FraudClient fraudClient
) {

    public Customer register(RegisterCustomerRequest request) {
        Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .phone(request.phone())
            .build();
        // todo: validate request
        customerRepository.saveAndFlush(customer);
        // todo: check unique

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse != null && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }
        // todo: send notification
        return customer;
    }
}
