package com.epam.rulerunner.order.event;

import com.epam.rulerunner.event.consumer.EventProcessor;
import com.epam.rulerunner.event.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FixEventProcessor implements EventProcessor<FixEvent> {

    private final static Logger LOG = LoggerFactory.getLogger(FixEventProcessor.class);

    public FixEventProcessor() {
    }

    @Override
    public void process(Event event, FixEvent payload) {
        LOG.info("[TODO] FIX message processed: {}", payload);
    }

}
