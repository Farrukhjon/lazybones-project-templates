package org.farrukh.examples.integration.handler;

import org.springframework.http.MediaType;
import org.springframework.messaging.Message;

/**
 * Filters greeting message.
 */
public class GreetingMessageFilterSelector {

    /**
     * Handles and filters a message.
     * @param message a message for filtering.
     * @return result of the filtering.
     */
    public boolean handleMessage(final Message<?> message) {
        String theExpectedTransformedGreeting = "Hello world! has been transformed";
        boolean isExpectedMediaType = message.getHeaders().get("media-type") == MediaType.APPLICATION_JSON;
        return isExpectedMediaType && message.getPayload() == theExpectedTransformedGreeting;
    }

}
