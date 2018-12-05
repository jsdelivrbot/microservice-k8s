package com.epam.rulerunner;

import com.epam.event.kafka.producer.config.EnableEpamKafkaProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = {
    "com.epam.rulerunner",
    "com.epam.rulerunner.order",
    "com.epam.rulerunner.order.config",
    "com.epam.service.advice"
})
//@EnableFeignClients(basePackages = {
//        "com.epam.contract.api.card_management.client",
//        "com.epam.contract.api.external.client"
//})
@EnableEpamKafkaProducer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
