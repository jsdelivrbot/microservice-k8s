package com.epam.event.consumer;

import com.epam.event.model.Event;
import com.epam.event.model.EventHeader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class WhitelistEventFilterTest {

    private Set<String> whitelist;
    private EventFilter eventFilter;

    @Before
    public void setUp() {
        whitelist = new HashSet<>();
        whitelist.add("Test1");
        whitelist.add("Test2");
        whitelist.add("Test3");

        eventFilter = new WhitelistEventFilter(whitelist);
    }

    @Test
    public void whenConstructedWithNullWhitelistThenIllegalArgumentExceptionIsThrown() {
        try {
            new WhitelistEventFilter(null);
        } catch (NullPointerException e) {
            Assert.assertEquals("whitelist must not be null", e.getMessage());
        }
    }

    @Test
    public void whenShouldAcceptCalledWithWhitelistedElementThenReturnsTrue() {
        // Given
        Event event = new Event(new EventHeader("id", "Test2", "format"), "payload");

        // When
        boolean actual = eventFilter.shouldAccept(event);

        // Then
        Assert.assertTrue(actual);
    }

    @Test
    public void whenShouldAcceptCalledWithNotWhitelistedElementThenReturnsFalse() {
        // Given
        Event event = new Event(new EventHeader("id", "SomethingElse", "format"), "payload");

        // When
        boolean actual = eventFilter.shouldAccept(event);

        // Then
        Assert.assertFalse(actual);
    }
}