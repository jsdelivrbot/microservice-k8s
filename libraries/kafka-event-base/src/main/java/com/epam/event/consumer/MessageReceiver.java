package com.epam.event.consumer;

/**
 * Connector for message handler tool (e.g.: Kafka) for listening on messages.
 */
public interface MessageReceiver {

    /**
     * Receives event message from the message broker (topic, queue).
     * <p>
     * Needs to be called by the event listener.
     *
     * @param message The event message that is received. Cannot be <code>null</code> or empty string (empty or only whitespaces).
     * @throws IllegalArgumentException if any of the argument constraints are violated.
     * @throws com.epam.event.model.EventException if error occurs during message processing.
     * @throws InvalidPayloadFormatException if the service is not configured to handle the payload.
     */
    void receive(String message);
}
