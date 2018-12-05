package com.epam.rulerunner.order.service.impl;

import com.epam.rulerunner.order.service.TimeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

//@AllArgsConstructor
public class TimeServiceImpl implements TimeService {

    private final TimeZone timeZone;

    public TimeServiceImpl(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now(timeZone.toZoneId());
    }

    @Override
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now(timeZone.toZoneId());
    }

    @Override
    public TimeZone getTimeZone() {
        return timeZone;
    }
}
