package com.epam.external;

import com.epam.external.config.ServiceConfig;
import org.springframework.boot.SpringApplication;

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConfig.class).start();
    }
}
