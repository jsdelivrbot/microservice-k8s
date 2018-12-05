package com.epam.event.consumer;

import com.epam.event.model.Event;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;
import static org.apache.commons.lang3.Validate.notEmpty;

/**
 * Routing event handler to delegate the message to the proper event handler.
 */
public class RoutingEventHandler implements EventHandler {

    private final Map<String, EventHandler> routes;

    /**
     * @param routes Cannot be <code>null</code>.
     * @throws IllegalArgumentException if any of the argument constraints are violated.
     */
    public RoutingEventHandler(Map<String, EventHandler> routes) {
        this.routes = unmodifiableMap(new HashMap<>(notEmpty(routes, "routes must not be empty")));
    }

    @Override
    public void handle(Event event, String payload) {
        EventHandler delegate = routes.get(event.getEventHeader().getEventType());
        if (delegate == null) {
            throw new IllegalStateException("Delegate for " + event.getEventHeader().getEventType() + " is not registered");
        }
        delegate.handle(event, payload);
    }
}
