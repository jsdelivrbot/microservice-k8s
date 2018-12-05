package com.epam.event.consumer;

import com.epam.event.model.Event;
import com.epam.event.model.EventException;
import com.epam.event.model.EventHeader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JsonMessageReceiverTest {

    public static final String TEST_CLASS = "TestClass";
    public static final String JSON_STRING = "JsonString";
    @Mock
    private EventFilter eventFilter;

    @Mock
    private EventHandler eventHandler;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MessageReceiver messageReceiver;

    @Before
    public void setUp() {
        messageReceiver = new JsonMessageReceiver(eventFilter, eventHandler, objectMapper);
    }

    @Test
    public void whenConstructedWithNullEventFilterThenIllegalArgumentExceptionIsThrown() {
        try {
            new JsonMessageReceiver(null, eventHandler, objectMapper);
        } catch (NullPointerException e) {
            Assert.assertEquals("eventFilter must not be null", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithNullEventHandlerThenIllegalArgumentExceptionIsThrown() {
        try {
            new JsonMessageReceiver(eventFilter, null, objectMapper);
        } catch (NullPointerException e) {
            Assert.assertEquals("eventHandler must not be null", e.getMessage());
        }
    }

    @Test
    public void whenReceiveCalledWithNullPayloadThenIllegalArgumentExceptionIsThrown() {
        try {
            messageReceiver.receive(null);
        } catch (NullPointerException e) {
            Assert.assertEquals("message must not be blank", e.getMessage());
        }
    }

    @Test
    public void whenReceiveCalledWithEmptyPayloadThenIllegalArgumentExceptionIsThrown() {
        try {
            messageReceiver.receive("   ");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("message must not be blank", e.getMessage());
        }
    }

    @Test
    public void whenReceiveCalledWithInvalidEventThenEventExceptionIsThrown() {
        try {
            messageReceiver.receive("{\"invalid\":\"true\"}");
        } catch (EventException e) {
            Assert.assertEquals("Could not parse event", e.getMessage());
        }
    }

    @Test
    public void whenReceiveCalledWithInvalidPayloadFormatThenEventExceptionIsThrown() {
        // Given
        Mockito.when(eventFilter.shouldAccept(Mockito.any())).thenReturn(true);

        // When
        try {
            messageReceiver.receive("{\"eventHeader\":{\"eventId\":\"a\",\"eventType\":\"TestClass\",\"payloadFormat\":\"InvalidFormat\"},\"payload\":\"{\\\"fieldA\\\":\\\"a\\\",\\\"fieldB\\\":\\\"b\\\"}\"}");
        } catch (InvalidPayloadFormatException e) {
            Assert.assertEquals("Invalid payload format", e.getMessage());
        }
    }

    @Test
    public void whenReceiveCalledWithMessageToFilterThenMessageIsIgnored() {
        // Given
        String input = "{\"eventHeader\":{\"eventId\":\"a\",\"eventType\":\"TestClass\",\"payloadFormat\":\"JsonString\"},\"payload\":\"{\\\"fieldA\\\":\\\"a\\\",\\\"fieldB\\\":\\\"b\\\"}\"}";
        Mockito.when(eventFilter.shouldAccept(Mockito.any())).thenReturn(false);

        // When
        messageReceiver.receive(input);

        // Then
        Mockito.verifyZeroInteractions(eventHandler);
    }

    @Test
    public void whenReceiveCalledWithMessageToProcessThenEventIsParsedAndPayloadSentToEventHandler() {
        // Given
        String input = "{\"eventHeader\":{\"eventId\":\"a\",\"eventType\":\"TestClass\",\"payloadFormat\":\"JsonString\"},\"payload\":\"{\\\"fieldA\\\":\\\"a\\\",\\\"fieldB\\\":\\\"b\\\"}\"}";
        String payload = "{\"fieldA\":\"a\",\"fieldB\":\"b\"}";
        Event expected = new Event(new EventHeader("a", TEST_CLASS, JSON_STRING), payload);
        Mockito.when(eventFilter.shouldAccept(Mockito.any())).thenReturn(true);

        // When
        messageReceiver.receive(input);

        // Then
        Mockito.verify(eventHandler).handle(expected, payload);
    }
}