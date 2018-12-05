package com.epam.event.consumer;

import com.epam.event.model.Event;
import com.epam.event.model.EventHeader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ClassSimpleNameWhitelistEventFilterTest {

    private Set<Class> whitelist;
    private EventFilter eventFilter;

    @Before
    public void setUp() {
        whitelist = new HashSet<>();
        whitelist.add(Boolean.class);
        whitelist.add(BigDecimal.class);
        whitelist.add(String.class);

        eventFilter = new ClassSimpleNameWhitelistEventFilter(whitelist);
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
    public void whenShouldAcceptCalledWithNullThenIllegalArgumentExceptionIsThrown() {
        try {
            eventFilter.shouldAccept(null);
        } catch (NullPointerException e) {
            Assert.assertEquals(null, e.getMessage());
        }
    }

    @Test
    public void whenShouldAcceptCalledWithWhitelistedElementThenReturnsTrue() {
        // Given
        Event event = new Event(new EventHeader("id", "BigDecimal", "format"), "payload");

        // When
        boolean actual = eventFilter.shouldAccept(event);

        // Then
        Assert.assertTrue(actual);
    }

    @Test
    public void whenShouldAcceptCalledWithNotWhitelistedElementThenReturnsFalse() {
        // Given
        Event event = new Event(new EventHeader("id", "Double", "format"), "payload");

        // When
        boolean actual = eventFilter.shouldAccept(event);

        // Then
        Assert.assertFalse(actual);
    }
}