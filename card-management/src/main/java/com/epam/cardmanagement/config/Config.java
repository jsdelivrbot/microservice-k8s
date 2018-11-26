package com.epam.cardmanagement.config;

import com.epam.cardmanagement.dao.CardCountryStatusRepository;
import com.epam.cardmanagement.dao.CardRegionStatusRepository;
import com.epam.cardmanagement.service.StatusCleanerService;
import com.epam.cardmanagement.service.TimeService;
import com.epam.cardmanagement.service.impl.StatusCleanerServiceImpl;
import com.epam.cardmanagement.service.impl.TimeServiceImpl;
import com.epam.service.advice.RestExceptionHandler;
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
            CardRegionStatusRepository cardRegionStatusRepository,
            CardCountryStatusRepository cardCountryStatusRepository,
            TimeService timeService) {
        return new StatusCleanerServiceImpl(
                cardRegionStatusRepository,
                cardCountryStatusRepository,
                timeService);
    }

    @Bean
    public RestExceptionHandler restExceptionHandler() {
        return new RestExceptionHandler();
    }

}
