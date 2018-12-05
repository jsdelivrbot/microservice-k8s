package com.epam.rulerunner.order.config;

import com.epam.rulerunner.order.service.StatusCleanerService;
import com.epam.rulerunner.order.service.TimeService;
import com.epam.rulerunner.order.service.impl.StatusCleanerServiceImpl;
import com.epam.rulerunner.order.service.impl.TimeServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class Config {

    @Bean
    public TimeService timeService(@Value("timezone") String timeZone) {
        return new TimeServiceImpl(TimeZone.getTimeZone(timeZone));
    }

    @Bean
    public StatusCleanerService statusCleanerService(
            TimeService timeService) {
        return new StatusCleanerServiceImpl(
                timeService);
    }

//    @Bean
//    public RestExceptionHandler restExceptionHandler() {
//        return new RestExceptionHandler();
//    }

}
