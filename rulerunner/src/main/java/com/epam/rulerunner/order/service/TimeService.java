package com.epam.rulerunner.order.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

public interface TimeService {

    LocalDate getCurrentDate();
    LocalDateTime getCurrentDateTime();
    TimeZone getTimeZone();
}
