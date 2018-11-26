package com.epam.cardmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = {
    "com.epam.cardmanagement",
    "com.epam.contract.api.card_management.config",
    "com.epam.contract.api.card_management.info",
    "com.epam.service.advice"
})
@EnableFeignClients(basePackages = {
        "com.epam.contract.api.card_management.client",
        "com.epam.contract.api.external.client"
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
