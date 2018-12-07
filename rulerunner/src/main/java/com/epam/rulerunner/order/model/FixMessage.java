package com.epam.rulerunner.order.model;

import java.util.Objects;

public class FixMessage {

    private String rawFixMessage;

    public FixMessage() {
    }

    public FixMessage(String rawFixMessage) {
        this.rawFixMessage = rawFixMessage;
    }

    public String getRawFixMessage() {
        return rawFixMessage;
    }

    public void setRawFixMessage(String rawFixMessage) {
        this.rawFixMessage = rawFixMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FixMessage that = (FixMessage) o;
        return Objects.equals(rawFixMessage, that.rawFixMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawFixMessage);
    }

}
