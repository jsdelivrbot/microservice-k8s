package com.epam.event.model;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Event class that contains metadata and the payload according to the payload format.
 */
public class Event {

    /**
     * Metadata for the event, that contains data without encryption.
     */
    private final EventHeader eventHeader;

    /**
     * Message payload in a format defined by the metadata.
     */
    private final String payload;

    /**
     * Default constructor for JSON message parser.
     */
    private Event() {
        eventHeader = null;
        payload = null;
    }

    /**
     * @param eventHeader Cannot be <code>null</code>.
     * @param payload     Cannot be <code>null</code> or empty string (empty or only whitespaces).
     * @throws IllegalArgumentException if any of the argument constraints are violated.
     */
    public Event(EventHeader eventHeader, String payload) {
        this.eventHeader = notNull(eventHeader, "eventHeader must not be null");
        this.payload = notBlank(payload, "payload must not be null");
    }

    public EventHeader getEventHeader() {
        return eventHeader;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (!eventHeader.equals(event.eventHeader)) return false;
        return payload.equals(event.payload);
    }

    @Override
    public int hashCode() {
        int result = eventHeader.hashCode();
        result = 31 * result + payload.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventHeader=" + eventHeader +
                ", payload='" + payload + '\'' +
                '}';
    }
}
