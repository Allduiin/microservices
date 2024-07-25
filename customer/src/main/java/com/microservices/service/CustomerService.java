package com.microservices.service;

import com.microservices.api.FraudCheckResponse;
import com.microservices.api.RegisterCustomerRequest;
import com.microservices.model.Customer;
import com.microservices.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
    CustomerRepository customerRepository,
    RestTemplate restTemplate
) {

    public static final String FRAUD_CHECK_ENDPOINT = "/api/v1/fraud-check/";
    public static final String DEFAULT_API_URL = "http://localhost:8081";

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

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
            DEFAULT_API_URL + FRAUD_CHECK_ENDPOINT + "{customerId}",
            FraudCheckResponse.class,
            customer.getId()
        );
        if (fraudCheckResponse != null && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }
        // todo: send notification
        return customer;
    }
}
