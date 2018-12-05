package com.epam.event.model;

import org.junit.Assert;
import org.junit.Test;

public class EventHeaderTest {

    @Test
    public void whenConstructedWithNullEventIdThenIllegalArgumentExceptionIsThrown() {
        try {
            new EventHeader(null, "a", "b");
        } catch (NullPointerException e) {
            Assert.assertEquals("eventId must not be empty", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithNullEventTypeThenIllegalArgumentExceptionIsThrown() {
        try {
            new EventHeader("a", null, "b");
        } catch (NullPointerException e) {
            Assert.assertEquals("eventType must not be empty", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithNullPayloadFormatThenIllegalArgumentExceptionIsThrown() {
        try {
            new EventHeader("b", "a", null);
        } catch (NullPointerException e) {
            Assert.assertEquals("payloadFormat must not be empty", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithEmptyEventIdThenIllegalArgumentExceptionIsThrown() {
        try {
            new EventHeader("    ", "a", "c");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("eventId must not be empty", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithEmptyEventTypeThenIllegalArgumentExceptionIsThrown() {
        try {
            new EventHeader("b", "    ", "c");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("eventType must not be empty", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithEmptyPayloadFormatThenIllegalArgumentExceptionIsThrown() {
        try {
            new EventHeader("b", "a", "    ");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("payloadFormat must not be empty", e.getMessage());
        }
    }

    @Test
    public void whenGetEventIdCalledThenReturnsExpectedValue() {
        // Given
        String expected = "aaaa";
        EventHeader eventHeader = new EventHeader("aaaa", "bbbb", "cccc");

        // When
        String actual = eventHeader.getEventId();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whenGetEventTypeCalledThenReturnsExpectedValue() {
        // Given
        String expected = "bbbb";
        EventHeader eventHeader = new EventHeader("aaaa", "bbbb", "cccc");

        // When
        String actual = eventHeader.getEventType();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whenGetPayloadFormatCalledThenReturnsExpectedValue() {
        // Given
        String expected = "cccc";
        EventHeader eventHeader = new EventHeader("aaaa", "bbbb", "cccc");

        // When
        String actual = eventHeader.getPayloadFormat();

        // Then
        Assert.assertEquals(expected, actual);
    }


}