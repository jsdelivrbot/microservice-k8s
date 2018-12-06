package com.epam.rulerunner.event.consumer;

import com.epam.rulerunner.event.model.Event;

/**
 * Event filter to decide if a message should be processed or not.
 */
public interface EventFilter {

    /**
     * Checks if the event should be processed or not.
     *
     * @param event Event with metadata. Cannot be <code>null</code>.
     * @return Should the event accepted (true) or ignored (false).
     * @throws IllegalArgumentException if any of the argument constraints are violated.
     */
    boolean shouldAccept(Event event);
}
