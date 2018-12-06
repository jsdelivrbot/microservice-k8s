package com.epam.rulerunner.event.consumer;

import com.epam.rulerunner.event.model.Event;
import com.epam.rulerunner.event.model.EventException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Parse payload and delegates the call to the typed event processor.
 *
 * @param <T> Type of the payload.
 */
public class JsonEventProcessorDelegate<T> implements EventHandler {

    private final Class<T> clazz;
    private final EventProcessor<T> eventProcessor;
    private final ObjectMapper objectMapper;

    /**
     * @param clazz          Cannot be <code>null</code>.
     * @param eventProcessor Cannot be <code>null</code>.
     * @throws IllegalArgumentException if any of the argument constraints are violated.
     */
    public JsonEventProcessorDelegate(Class<T> clazz, EventProcessor<T> eventProcessor, ObjectMapper objectMapper) {
        this.clazz = notNull(clazz, "clazz must not be null");
        this.eventProcessor = notNull(eventProcessor, "eventProcessor must not be null");
        this.objectMapper = notNull(objectMapper, "objectMapper must not be null");
    }

    @Override
    public void handle(Event event, String payload) {
        try {
            Object payloadObject = objectMapper.readValue(payload, clazz);
            eventProcessor.process(event, clazz.cast(payloadObject));
        } catch (IOException e) {
            throw new EventException("Could not parse payload", e, event);
        }
    }

}
