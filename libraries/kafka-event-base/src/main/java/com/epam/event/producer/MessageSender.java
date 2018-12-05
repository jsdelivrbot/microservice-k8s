package com.epam.event.producer;

/**
 * Connector for message handler tool (e.g.: Kafka) for publishing messages.
 */
public interface MessageSender {

    /**
     * Sends event message to the message broker (topic, queue).
     * <p>
     * Needs to be implemented to delegate the message to the proper message handler.
     *
     * @param message The event message that is sent. Cannot be <code>null</code> or empty string (empty or only whitespaces).
     * @throws IllegalArgumentException if any of the argument constraints are violated.
     */
    void send(String message);

}
