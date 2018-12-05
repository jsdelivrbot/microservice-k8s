package com.epam.event.consumer;

import com.epam.event.model.Event;

/**
 * Event processor for handling an already parsed payload.
 *
 * @param <T> Type of the payload object.
 */
public interface EventProcessor<T> {

    /**
     * Process payload.
     *
     * @param event Original event with metadata. Cannot be <code>null</code>.
     * @param payload Payload object. Cannot be <code>null</code>.
     * @throws IllegalArgumentException if any of the argument constraints are violated.
     */
    void process(Event event, T payload);

}
