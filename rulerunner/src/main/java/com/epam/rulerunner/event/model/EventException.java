package com.epam.rulerunner.event.model;

/**
 * Event handling base exception.
 */
public class EventException extends RuntimeException {

    /**
     * The event if it exists at the point where the exception is thrown. Can be <code>null</code>.
     */
    private Event event;

    public EventException(String message) {
        super(message);
    }

    public EventException(String message, Event event) {
        super(message);

        this.event = event;
    }

    public EventException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventException(String message, Throwable cause, Event event) {
        super(message, cause);

        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
