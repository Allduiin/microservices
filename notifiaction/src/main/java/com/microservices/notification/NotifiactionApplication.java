package com.microservices.notification;

import com.microservices.amqp.RabbitMQMessageProducer;
import com.microservices.notification.config.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {
    "com.microservices.notification",
    "com.microservices.amqp"
})
@EnableEurekaClient
public class NotifiactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotifiactionApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//        RabbitMQMessageProducer producer,
//        NotificationConfig notificationConfig
//    ) {
//        return args -> {
//            producer.publish(
//                new Car("Mustang", 540),
//                notificationConfig.getInternalExchange(),
//                notificationConfig.getInternalNotificationRoutingKey());
//        };
//    }
//
//    record Car(String label, int horsePower) {}
}
