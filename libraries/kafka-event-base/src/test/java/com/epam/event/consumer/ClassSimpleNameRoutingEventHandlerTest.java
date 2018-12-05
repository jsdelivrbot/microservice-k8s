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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ClassSimpleNameRoutingEventHandlerTest {

    private EventHandler eventHandler;
    private Map<Class, EventHandler> routes;

    @Mock
    private EventHandler eventHandlerForInteger;

    @Mock
    private EventHandler eventHandlerForBigDecimal;

    @Before
    public void setUp() {
        routes = new HashMap<>();
        routes.put(BigDecimal.class, eventHandlerForBigDecimal);
        routes.put(Integer.class, eventHandlerForInteger);

        eventHandler = new ClassSimpleNameRoutingEventHandler(routes);
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
    public void whenHandleCalledWithNullEventThenIllegalArgumentExceptionIsThrown() {
        try {
            eventHandler.handle(null, "Test");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Parameter 'event' cannot be null", e.getMessage());
        }
    }

    @Test
    public void whenHandleCalledWithNullPayloadThenIllegalArgumentExceptionIsThrown() {
        try {
            eventHandler.handle(new Event(new EventHeader("a", "b", "c"), "d"), null);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Parameter 'payload' cannot be null", e.getMessage());
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
        Event event = new Event(new EventHeader("id", "BigDecimal", "format"), payload);

        // When
        eventHandler.handle(event, payload);

        // Then
        Mockito.verify(eventHandlerForBigDecimal).handle(event, payload);
        Mockito.verifyZeroInteractions(eventHandlerForInteger);
    }

    @Test
    public void whenHandleCalledWithEmptyPayloadThenIllegalStateExceptionIsThrown() {
        // Given
        String payload = "content";
        Event event = new Event(new EventHeader("id", "Boolean", "format"), payload);

        // When
        try {
            eventHandler.handle(event, payload);
        } catch (IllegalStateException e) {
            Assert.assertEquals("Delegate for Boolean is not registered", e.getMessage() );
        }
    }
}