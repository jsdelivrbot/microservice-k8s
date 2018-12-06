package com.epam.rulerunner.event.consumer;

import com.epam.rulerunner.event.model.Event;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Simple whitelist filtering.
 */
public class WhitelistEventFilter implements EventFilter {

    private final Set<String> whitelist;

    /**
     * @param whitelist Cannot be <code>null</code>.
     * @throws IllegalArgumentException if any of the argument constraints are violated.
     */
    public WhitelistEventFilter(Set<String> whitelist) {
        this.whitelist = Collections.unmodifiableSet(new HashSet<>(notNull(whitelist, "whitelist must not be null")));
    }

    @Override
    public boolean shouldAccept(Event event) {
        return whitelist.contains(event.getEventHeader().getEventType());
    }
}
