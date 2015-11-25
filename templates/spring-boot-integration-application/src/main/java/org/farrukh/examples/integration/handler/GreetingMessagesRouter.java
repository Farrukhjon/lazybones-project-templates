package org.farrukh.examples.integration.handler;

import org.springframework.http.MediaType;
import org.springframework.integration.annotation.Router;
import org.springframework.messaging.Message;

/**
 * Simple message router.
 */
public class GreetingMessagesRouter {

    /**
     * Checks and route a message depending on content-type.
     * @param greeting a message to be route.
     * @return the channel name.
     */
    @Router
    public String route(final Message<?> greeting) {
        String filteredChannel = "ignoredChannel";
        String contentType = (String) greeting.getHeaders().get("content-type");
        if (contentType.equals(MediaType.APPLICATION_JSON_VALUE)) {
            filteredChannel = "greetingChannel";
        }
        return filteredChannel;
    }

}
