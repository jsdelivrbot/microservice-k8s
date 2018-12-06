package com.epam.rulerunner.event.consumer;

import com.epam.rulerunner.event.model.Event;
import com.epam.rulerunner.event.model.EventException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Default handler for processing a JSON message in string format. It also handles message filtering.
 */
public class JsonMessageReceiver implements MessageReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(JsonMessageReceiver.class);
    private static final String PAYLOAD_FORMAT = "JsonString";

    private final ObjectMapper objectMapper;
    private final EventFilter eventFilter;
    private final EventHandler eventHandler;

    public JsonMessageReceiver(EventFilter eventFilter, EventHandler eventHandler, ObjectMapper objectMapper) {
        this.eventFilter = notNull(eventFilter, "eventFilter must not be null");
        this.eventHandler = notNull(eventHandler, "eventHandler must not be null");
        this.objectMapper = notNull(objectMapper, "objectMapper, must not be null");
    }

    @Override
    public void receive(String message) {
        notBlank(message, "message must not be blank");
        Event event = parseEvent(message);
        if (eventFilter.shouldAccept(event)) {
            processEvent(event);
        } else {
            LOG.debug("Ignoring event");
        }
    }

    private void processEvent(Event event) {
        LOG.debug("Processing event with header: {}", event.getEventHeader());
        if (validPayloadFormat(event)) {
            LOG.debug("Valid format");
            String payload = retrievePayload(event);
            eventHandler.handle(event, payload);
            LOG.debug("Event processed");
        } else {
            LOG.debug("Invalid format");
            throw new InvalidPayloadFormatException(event);
        }
    }

    private Event parseEvent(String message) {
        LOG.info("Received json message: " + message);
        Event event;
        try {
            event = objectMapper.readValue(message, Event.class);
        } catch (IOException e) {
            throw new EventException("Could not parse event", e);
        }
        return event;
    }

    private String retrievePayload(Event event) {
        return event.getPayload();
    }

    private boolean validPayloadFormat(Event event) {
        return PAYLOAD_FORMAT.equals(event.getEventHeader().getPayloadFormat());
    }
}
