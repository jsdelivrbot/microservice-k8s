package com.epam.cardmanagement.service.impl;

import com.epam.cardmanagement.service.TimeService;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

@AllArgsConstructor
public class TimeServiceImpl implements TimeService {

    private final TimeZone timeZone;

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
