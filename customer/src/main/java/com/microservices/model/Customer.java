package com.microservices.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
