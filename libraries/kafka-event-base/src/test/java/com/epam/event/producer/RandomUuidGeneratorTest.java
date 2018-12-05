package com.epam.event.producer;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class RandomUuidGeneratorTest {

    private UuidGenerator uuidGenerator;

    @Before
    public void setUp() {
        uuidGenerator = new RandomUuidGenerator();
    }

    @Test
    public void whenGenerateUuidIsCalledThenReturnsValidUuidString() {
        // When
        String actual = uuidGenerator.generateUuid();

        // Then
        UUID.fromString(actual);
    }

}