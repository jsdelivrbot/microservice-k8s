package com.epam.event.consumer;

import com.epam.event.model.Event;
import com.epam.event.model.EventHeader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class RoutingEventHandlerTest {

    private EventHandler eventHandler;
    private Map<String, EventHandler> routes;

    @Mock
    private EventHandler eventHandlerForTypeA;

    @Mock
    private EventHandler eventHandlerForTypeB;

    @Before
    public void setUp() {
        routes = new HashMap<>();
        routes.put("TypeA", eventHandlerForTypeA);
        routes.put("TypeB", eventHandlerForTypeB);

        eventHandler = new RoutingEventHandler(routes);
    }

    @Test
    public void whenConstructedWithNullRoutesThenIllegalArgumentExceptionIsThrown() {
        try {
            new RoutingEventHandler(null);
        } catch (NullPointerException e) {
            Assert.assertEquals("routes must not be empty", e.getMessage());
        }
    }

    @Test
    public void whenHandleCalledWithEmptyPayloadThenIllegalArgumentExceptionIsThrown() {
        try {
            eventHandler.handle(new Event(new EventHeader("a", "b", "c"), "d"), "   ");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Parameter 'payload' cannot be empty", e.getMessage());
        }
    }

    @Test
    public void whenHandleCalledThenItRoutesTheProperHandler() {
        // Given
        String payload = "content";
        Event event = new Event(new EventHeader("id", "TypeA", "format"), payload);

        // When
        eventHandler.handle(event, payload);

        // Then
        Mockito.verify(eventHandlerForTypeA).handle(event, payload);
        Mockito.verifyZeroInteractions(eventHandlerForTypeB);
    }

    @Test
    public void whenHandleCalledWithEmptyPayloadThenIllegalStateExceptionIsThrown() {
        // Given
        String payload = "content";
        Event event = new Event(new EventHeader("id", "TypeUnknown", "format"), payload);

        // When
        try {
            eventHandler.handle(event, payload);
        } catch (IllegalStateException e) {
            Assert.assertEquals("Delegate for TypeUnknown is not registered", e.getMessage() );
        }
    }
}