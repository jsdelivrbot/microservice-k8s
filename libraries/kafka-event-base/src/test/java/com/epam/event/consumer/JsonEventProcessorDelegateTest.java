package com.epam.event.consumer;

import com.epam.event.model.Event;
import com.epam.event.model.EventException;
import com.epam.event.model.EventHeader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class JsonEventProcessorDelegateTest {

    private EventHandler eventHandler;

    @Mock
    private EventProcessor<TestClass> eventProcessor;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        eventHandler = new JsonEventProcessorDelegate<TestClass>(TestClass.class, eventProcessor, objectMapper);
    }

    @Test
    public void whenConstructedWithNullClazzThenIllegalArgumentExceptionIsThrown() {
        try {
            new JsonEventProcessorDelegate(null, eventProcessor, objectMapper);
        } catch (NullPointerException e) {
            Assert.assertEquals("clazz must not be null", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithNullEventProcessorThenIllegalArgumentExceptionIsThrown() {
        try {
            new JsonEventProcessorDelegate(TestClass.class, null, objectMapper);
        } catch (NullPointerException e) {
            Assert.assertEquals("eventProcessor must not be null", e.getMessage());
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
            eventHandler.handle(new Event(new EventHeader("a", "b", "c"), "{ \"message\": \"hello\"}"), "{ \"message\": \"hello\"}");
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
    public void whenHandleCalledThenDelegatesToEventProcessor() {
        // Given
        TestClass expected = new TestClass();
        expected.setFieldA("a");
        expected.setFieldB("b");
        String payload = "{\"fieldA\":\"a\", \"fieldB\":\"b\"}";
        Event event = new Event(new EventHeader("a", "b", "c"), payload);

        // When
        eventHandler.handle(event, payload);

        // Then
        Mockito.verify(eventProcessor).process(event, expected);
    }

    @Test
    public void whenHandleCalledWithMissingOptionalFieldsThenDelegatesToEventProcessor() {
        // Given
        TestClass expected = new TestClass();
        expected.setFieldA("a");
        String payload = "{\"fieldA\":\"a\"}";
        Event event = new Event(new EventHeader("a", "b", "c"), payload);

        // When
        eventHandler.handle(event, payload);

        // Then
        Mockito.verify(eventProcessor).process(event, expected);
    }


    @Test
    public void whenHandleCalledWithInvalidPaylodThenIllegalArgumentExceptionIsThrown() {
        try {
            eventHandler.handle(new Event(new EventHeader("a", "b", "c"), "d"),"{\"fieldX\":\"a\"}");
        } catch (EventException e) {
            Assert.assertEquals("Could not parse payload", e.getMessage());
        }
    }

}