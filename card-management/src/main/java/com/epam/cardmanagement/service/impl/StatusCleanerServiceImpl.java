package com.epam.cardmanagement.service.impl;

import com.epam.cardmanagement.dao.CardCountryStatusRepository;
import com.epam.cardmanagement.dao.CardRegionStatusRepository;
import com.epam.cardmanagement.service.StatusCleanerService;
import com.epam.cardmanagement.service.TimeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@AllArgsConstructor
@Slf4j
public class StatusCleanerServiceImpl implements StatusCleanerService {

    private final CardRegionStatusRepository cardRegionStatusRepository;
    private final CardCountryStatusRepository cardCountryStatusRepository;
    private final TimeService timeService;

    @Override
    @Scheduled(cron = "${scheduler.clean.cron}")
    public void removeExpiredStatuses() {
        LOG.debug("Scheduled cleaning of country and region statuses started.");
        LocalDate currentDate = timeService.getCurrentDate();
        cleanCountryStatuses(currentDate);
        cleanRegionStatuses(currentDate);
        LOG.debug("Scheduled cleaning of country and region statuses finished.");
    }

    private void cleanRegionStatuses(LocalDate currentDate) {
        int deleteExpiredRegionStatusCount = cardRegionStatusRepository.deleteExpired(currentDate);
        if (deleteExpiredRegionStatusCount > 0) {
            LOG.info("Deleted " + deleteExpiredRegionStatusCount + " expired region statuses.");
        }
    }

    private void cleanCountryStatuses(LocalDate currentDate) {
        int deleteExpiredCountryStatusCount = cardCountryStatusRepository.deleteExpired(currentDate);
        if (deleteExpiredCountryStatusCount > 0) {
            LOG.info("Deleted " + deleteExpiredCountryStatusCount + " expired country statuses.");
        }
    }
}
