package com.microservices.customer.service;

import com.microservices.amqp.RabbitMQMessageProducer;
import com.microservices.customer.api.RegisterCustomerRequest;
import com.microservices.clients.fraud.FraudCheckResponse;
import com.microservices.clients.fraud.FraudClient;
import com.microservices.clients.notification.NotificationRequest;
import com.microservices.customer.model.Customer;
import com.microservices.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
    CustomerRepository customerRepository,
    FraudClient fraudClient,
    RabbitMQMessageProducer rabbitMQMessageProducer
) {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

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
        log.info("Got response from fraud: {}", fraudCheckResponse);
        if (fraudCheckResponse != null && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }
        NotificationRequest notificationRequest = new NotificationRequest(
            customer.getId(),
            customer.getFirstName(),
            customer.getLastName(),
            customer.getEmail(),
            customer.getPhone()
        );
        log.info("Sending request to notification queue: {}", notificationRequest);
        rabbitMQMessageProducer.publish(
            notificationRequest,
            "internal.exchange",
            "internal.notification.routing-key"
        );
        return customer;
    }
}
