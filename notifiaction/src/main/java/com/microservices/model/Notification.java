package com.microservices.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notification {
    @Id
    @SequenceGenerator(
        name = "notification_sequence",
        sequenceName = "notification_id_sequence"
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "notification_id_sequence"
    )
    private int id;
    private Integer customerId;
    private LocalDateTime createdAt;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
