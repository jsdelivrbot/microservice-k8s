package com.epam.rulerunner.order.event;

import java.io.Serializable;
import java.util.Objects;

public class FixEvent implements Serializable {
    private String fixMessage;

    public FixEvent() {
    }

    public FixEvent(String fixMessage) {
        this.fixMessage = fixMessage;
    }

    public String getFixMessage() {
        return fixMessage;
    }

    public void setFixMessage(String fixMessage) {
        this.fixMessage = fixMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FixEvent that = (FixEvent) o;
        return Objects.equals(fixMessage, that.fixMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixMessage);
    }

    @Override
    public String toString() {
        return "FixMessage[" + fixMessage + ']';
    }
}
