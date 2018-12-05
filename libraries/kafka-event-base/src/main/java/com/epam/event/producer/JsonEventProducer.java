package com.epam.event.producer;

import com.epam.event.model.Event;
import com.epam.event.model.EventException;
import com.epam.event.model.EventHeader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Default producer for handling JSON payload and JSON events.
 */
public class JsonEventProducer implements EventProducer {

    private static final Logger LOG = LoggerFactory.getLogger(JsonEventProducer.class);
    private static final String PAYLOAD_FORMAT = "JsonString";

    private final MessageSender messageSender;
    private final String serviceId;
    private final UuidGenerator uuidGenerator;
    private final ObjectMapper objectMapper;

    /**
     * @param messageSender Cannot be <code>null</code>.
     * @param serviceId     Cannot be <code>null</code> or empty string (empty or only whitespaces).
     * @param uuidGenerator Cannot be <code>null</code>.
     * @param objectMapper  Cannot be <code>null</code>.
     * @throws IllegalArgumentException if any of the argument constraints are violated.
     */
    public JsonEventProducer(MessageSender messageSender, String serviceId, UuidGenerator uuidGenerator, ObjectMapper objectMapper) {
        this.messageSender = notNull(messageSender, "messageSender must not be null");
        this.serviceId = notBlank(serviceId, "serviceId must not be null");
        this.uuidGenerator = notNull(uuidGenerator, "uuidGenerator must not be null");
        this.objectMapper = notNull(objectMapper, "objectMapper must not be null");
    }

    @Override
    public Event send(Object payload) {
        notNull(payload, "payload must not be null");
        Event event = createEvent(payload);
        String eventString = mapEventToJsonString(event);
        LOG.debug("Sending event with header: {}", event.getEventHeader());
        messageSender.send(eventString);
        LOG.debug("Event sent");

        return event;
    }

    private Event createEvent(Object payload) {
        PayloadStringWithFormat payloadStringWithFormat = mapPayloadToString(payload);
        EventHeader eventHeader = generateEventHeader(payload, payloadStringWithFormat.getFormat());
        return new Event(eventHeader, payloadStringWithFormat.getPayload());
    }

    protected PayloadStringWithFormat mapPayloadToString(Object payload) {
        try {
            return new PayloadStringWithFormat(PAYLOAD_FORMAT, objectMapper.writeValueAsString(payload));
        } catch (JsonProcessingException e) {
            throw new EventException("Could not parse payload of class " + payload.getClass() + " to json", e);
        }
    }

    private EventHeader generateEventHeader(Object payload, String payloadFormat) {
        String eventId = serviceId + "-" + uuidGenerator.generateUuid();
        String eventType = payload.getClass().getSimpleName();
        return new EventHeader(eventId, eventType, payloadFormat);
    }

    private String mapEventToJsonString(Event event) {
        String eventString;
        try {
            eventString = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new EventException("Could not parse event to json", e.getCause(), event);
        }
        return eventString;
    }

}
