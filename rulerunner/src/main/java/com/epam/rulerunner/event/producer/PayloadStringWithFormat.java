package com.epam.rulerunner.event.producer;

import static org.apache.commons.lang3.Validate.notBlank;

/**
 * A payload message with its format definition.
 */
public class PayloadStringWithFormat {

    /**
     * Format definition of the payload.
     */
    private final String format;
    /**
     * Payload in the defined format.
     */
    private final String payload;

    /**
     * @param format  Cannot be <code>null</code> or empty string (empty or only whitespaces).
     * @param payload Cannot be <code>null</code> or empty string (empty or only whitespaces).
     * @throws IllegalArgumentException if any of the argument constraints are violated.
     */
    public PayloadStringWithFormat(String format, String payload) {
        this.format = notBlank(format, "format must not be blank");
        this.payload = notBlank(payload, "payload must not be null");
    }

    public String getFormat() {
        return format;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PayloadStringWithFormat that = (PayloadStringWithFormat) o;

        if (!format.equals(that.format)) return false;
        return payload.equals(that.payload);
    }

    @Override
    public int hashCode() {
        int result = format.hashCode();
        result = 31 * result + payload.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PayloadStringWithFormat{" +
                "format='" + format + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}
