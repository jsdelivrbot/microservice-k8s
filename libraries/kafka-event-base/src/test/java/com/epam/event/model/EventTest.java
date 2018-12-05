package com.epam.event.model;

import org.junit.Assert;
import org.junit.Test;

public class EventTest {

    @Test
    public void whenConstructedWithNullEventHeaderThenIllegalArgumentExceptionIsThrown() {
        try {
            new Event(null, "a");
        } catch (NullPointerException e) {
            Assert.assertEquals("eventHeader must not be null", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithNullPayloadThenIllegalArgumentExceptionIsThrown() {
        try {
            new Event(new EventHeader("a","b","c"), null);
        } catch (NullPointerException e) {
            Assert.assertEquals("payload must not be null", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithEmptyPayloadThenIllegalArgumentExceptionIsThrown() {
        try {
            new Event(new EventHeader("a","b","c"), "a");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Parameter 'payload' cannot be empty", e.getMessage());
        }
    }

    @Test
    public void whenGetEventHeaderCalledThenReturnsExpectedValue() {
        // Given
        EventHeader expected = new EventHeader("a","b","c");
        Event event = new Event(expected, "bbbb");

        // When
        EventHeader actual = event.getEventHeader();

        // Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void whenGetPayloadCalledThenReturnsExpectedValue() {
        // Given
        String expected = "bbbb";
        Event event = new Event(new EventHeader("a","b","c"), "bbbb");

        // When
        String actual = event.getPayload();

        // Then
        Assert.assertEquals(expected,actual);
    }

}