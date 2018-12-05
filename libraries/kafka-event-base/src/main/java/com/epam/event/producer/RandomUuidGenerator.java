package com.epam.event.producer;

import java.util.UUID;

/**
 * Random UUID Generator.
 */
public class RandomUuidGenerator implements UuidGenerator {

    @Override
    public String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
