package com.epam.event.model;

import static org.apache.commons.lang3.Validate.notBlank;

/**
 * Event header class that contains the meteadata for the event.
 * <p>
 * As event headers needs to be backward compatible, the fields cannot be modified. Only new non-mandatory fields can
 * be added.
 */
public class EventHeader {

    /**
     * Unique ID of the event that can be used for tracing.
     */
    private final String eventId;

    /**
     * Type of the event payload. Usually the class name of the payload is used as a type. The classes cannot be
     * changed, only new classes can be created in case of a change on event level.
     */
    private final String eventType;

    /**
     * The format of the payload. Currently handled formats are the following:
     * - JsonString - For escaped JSON string.
     * - EncryptedJsonString-{key} - For encrypted JSON string with the given {key} ID.
     */
    private final String payloadFormat;

    /**
     * Default constructor for JSON message parser.
     */
    private EventHeader() {
        eventId = null;
        eventType = null;
        payloadFormat = null;
    }

    /**
     * @param eventId       Cannot be <code>null</code> or empty string (empty or only whitespaces).
     * @param eventType     Cannot be <code>null</code> or empty string (empty or only whitespaces).
     * @param payloadFormat Cannot be <code>null</code> or empty string (empty or only whitespaces).
     * @throws IllegalArgumentException if any of the argument constraints are violated.
     */
    public EventHeader(String eventId, String eventType, String payloadFormat) {
        this.eventId = notBlank(eventId, "eventId must not be empty");
        this.eventType = notBlank(eventType, "eventType must not be empty");
        this.payloadFormat = notBlank(payloadFormat, "payloadFormat must not be empty");
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getPayloadFormat() {
        return payloadFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventHeader that = (EventHeader) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!eventType.equals(that.eventType)) return false;
        return payloadFormat.equals(that.payloadFormat);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + eventType.hashCode();
        result = 31 * result + payloadFormat.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EventHeader{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", payloadFormat='" + payloadFormat + '\'' +
                '}';
    }
}
