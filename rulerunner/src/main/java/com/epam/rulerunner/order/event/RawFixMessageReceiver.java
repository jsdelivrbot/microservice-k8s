package com.epam.rulerunner.order.event;

import com.epam.rulerunner.event.consumer.EventFilter;
import com.epam.rulerunner.event.consumer.EventHandler;
import com.epam.rulerunner.event.consumer.MessageReceiver;
import com.epam.rulerunner.event.model.Event;
import com.epam.rulerunner.event.model.EventHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Default handler for processing a FIX message in string format. It also handles message filtering.
 */
public class RawFixMessageReceiver implements MessageReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(RawFixMessageReceiver.class);
    private static final String PAYLOAD_FORMAT = "FixMessage";
    private static final String EVENT_TYPE = FixEvent.class.getSimpleName();

    private final EventFilter eventFilter;
    private final EventHandler eventHandler;

    public RawFixMessageReceiver(EventFilter eventFilter, EventHandler eventHandler) {
        this.eventFilter = notNull(eventFilter, "eventFilter must not be null");
        this.eventHandler = notNull(eventHandler, "eventHandler must not be null");
    }

    @Override
    public void receive(String message) {
        notBlank(message, "message must not be blank");
        Event event = createEvent(message);
        if (eventFilter.shouldAccept(event)) {
            processEvent(event);
        } else {
            LOG.debug("Ignoring event");
        }
    }

    private void processEvent(Event event) {
        LOG.debug("Processing event with header: {}", event.getEventHeader());
        eventHandler.handle(event, retrievePayload(event));
        LOG.debug("Event processed");
    }

    private Event createEvent(String message) {
        LOG.info("Received fix message: " + message);
        return new Event(createEventHeader(), message);
    }

    private EventHeader createEventHeader() {
        return new EventHeader(UUID.randomUUID().toString(), EVENT_TYPE, PAYLOAD_FORMAT);
    }

    private String retrievePayload(Event event) {
        return event.getPayload();
    }
}
