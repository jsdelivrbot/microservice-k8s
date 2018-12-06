package com.epam.rulerunner.event.consumer;

import com.epam.rulerunner.event.model.Event;

/**
 * Handler for processing the payload string.
 */
public interface EventHandler {

    /**
     * Handles payload based on the event metadata.
     *
     * @param event Original event that contains metadata. Cannot be <code>null</code>.
     * @param payload Payload in string format. Cannot be <code>null</code> or empty string (empty or only whitespaces).
     * @throws IllegalArgumentException if any of the argument constraints are violated.
     */
    void handle(Event event, String payload);
}
