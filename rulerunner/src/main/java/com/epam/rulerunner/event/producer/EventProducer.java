package com.epam.rulerunner.event.producer;

import com.epam.rulerunner.event.model.Event;

/**
 * Event producer for distributing events.
 */
public interface EventProducer {

    /**
     * Wraps a payload into an event and sens to the message broker.
     *
     * @param payload Payload. Cannot be <code>null</code>.
     * @return The sent event.
     * @throws IllegalArgumentException if any of the argument constraints are violated.
     */
    Event send(Object payload);
}
