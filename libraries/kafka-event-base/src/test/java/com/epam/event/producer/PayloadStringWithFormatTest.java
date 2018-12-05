package com.epam.event.producer;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class PayloadStringWithFormatTest {

    @Test
    public void whenConstructedWithNullFormatThenIllegalArgumentExceptionIsThrown() {
        try {
            new PayloadStringWithFormat(null, "a");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Parameter 'format' cannot be null", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithNullPayloadThenIllegalArgumentExceptionIsThrown() {
        try {
            new PayloadStringWithFormat("a", null);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Parameter 'payload' cannot be null", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithEmptyFormatThenIllegalArgumentExceptionIsThrown() {
        try {
            new PayloadStringWithFormat("    ", "a");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Parameter 'format' cannot be empty", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithEmptyPayloadThenIllegalArgumentExceptionIsThrown() {
        try {
            new PayloadStringWithFormat("a", "   ");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Parameter 'payload' cannot be empty", e.getMessage());
        }
    }

    @Test
    public void whenGetFormatCalledThenReturnsExpectedValue() {
        // Given
        String expected = "aaaa";
        PayloadStringWithFormat payloadStringWithFormat = new PayloadStringWithFormat("aaaa", "bbbb");

        // When
        String actual = payloadStringWithFormat.getFormat();

        // Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void whenGetPayloadCalledThenReturnsExpectedValue() {
        // Given
        String expected = "bbbb";
        PayloadStringWithFormat payloadStringWithFormat = new PayloadStringWithFormat("aaaa", "bbbb");

        // When
        String actual = payloadStringWithFormat.getPayload();

        // Then
        Assert.assertEquals(expected,actual);
    }

}