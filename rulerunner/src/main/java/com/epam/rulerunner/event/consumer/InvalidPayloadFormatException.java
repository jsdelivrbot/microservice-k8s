package com.epam.rulerunner.event.consumer;

import com.epam.rulerunner.event.model.Event;
import com.epam.rulerunner.event.model.EventException;

/**
 * Exception for invalid payload format. E.g.: when encrypted message is expected but 'JsonString' is received.
 */
public class InvalidPayloadFormatException extends EventException {


    public InvalidPayloadFormatException(Event event) {
        super("Invalid payload format", event);
    }

    public InvalidPayloadFormatException(String message, Event event) {
        super(message, event);
    }

    public InvalidPayloadFormatException(String message, Throwable cause, Event event) {
        super(message, cause, event);
    }
}
