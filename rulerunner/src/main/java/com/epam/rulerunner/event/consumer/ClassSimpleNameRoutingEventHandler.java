package com.epam.rulerunner.event.consumer;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ClassSimpleNameRoutingEventHandler extends RoutingEventHandler {

    public ClassSimpleNameRoutingEventHandler(Map<Class, EventHandler> routes) {
        super(convertClassesToString(routes));
    }

    private static Map<String, EventHandler> convertClassesToString(Map<Class, EventHandler> routes) {
        return routes.entrySet()
                .stream()
                .collect(toMap(e -> e.getKey().getSimpleName(), e -> e.getValue()));
    }

}
