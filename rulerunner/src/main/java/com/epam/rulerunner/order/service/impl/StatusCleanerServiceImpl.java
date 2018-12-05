package com.epam.rulerunner.order.service.impl;

import com.epam.rulerunner.order.service.StatusCleanerService;
import com.epam.rulerunner.order.service.TimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

//@AllArgsConstructor
//@Slf4j
public class StatusCleanerServiceImpl implements StatusCleanerService {

    private final static Logger LOG = LoggerFactory.getLogger(StatusCleanerServiceImpl.class);

    private final TimeService timeService;

    public StatusCleanerServiceImpl(TimeService timeService) {
        this.timeService = timeService;
    }

    @Override
    @Scheduled(cron = "${scheduler.clean.cron}")
    public void removeExpiredStatuses() {
        LOG.debug("Scheduled cleaning of country and region statuses started.");
        LocalDate currentDate = timeService.getCurrentDate();
//        cleanCountryStatuses(currentDate);
//        cleanRegionStatuses(currentDate);
        LOG.debug("Scheduled cleaning of country and region statuses finished.");
    }

//    private void cleanRegionStatuses(LocalDate currentDate) {
//        int deleteExpiredRegionStatusCount = cardRegionStatusRepository.deleteExpired(currentDate);
//        if (deleteExpiredRegionStatusCount > 0) {
//            LOG.info("Deleted " + deleteExpiredRegionStatusCount + " expired region statuses.");
//        }
//    }
//
//    private void cleanCountryStatuses(LocalDate currentDate) {
//        int deleteExpiredCountryStatusCount = cardCountryStatusRepository.deleteExpired(currentDate);
//        if (deleteExpiredCountryStatusCount > 0) {
//            LOG.info("Deleted " + deleteExpiredCountryStatusCount + " expired country statuses.");
//        }
//    }
}
