package com.epam.event.producer;

/**
 * UUID generator.
 */
public interface UuidGenerator {

    /**
     * Generates a new UUID.
     * @return Generated UUID in String format.
     */
    String generateUuid();
}
