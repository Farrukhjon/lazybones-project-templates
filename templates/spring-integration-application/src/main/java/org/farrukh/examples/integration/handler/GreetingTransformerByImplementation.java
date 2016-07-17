package org.farrukh.examples.integration.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.transformer.Transformer;
import org.springframework.messaging.Message;

/**
 * Greeting transformer endpoint.
 */
public class GreetingTransformerByImplementation implements Transformer {

    /**
     * Logger.
     */
    @SuppressWarnings("constantname")
    private static final Logger logger = LoggerFactory.getLogger(GreetingTransformerByImplementation.class);

    /**
     * Just transforms a given message and returns transformed message.
     * @param message original message
     * @return transformed message
     */
    @Override
    public Message<?> transform(final Message<?> message) {
        String oldPayload = (String) message.getPayload();
        String newPayload = oldPayload + " has been transformed";
        Message transformedMessage = MessageBuilder.withPayload(newPayload).copyHeaders(message.getHeaders()).build();
        logger.info("Message {} was handled and transformed by {}", message, GreetingTransformerByImplementation.class);
        return transformedMessage;
    }
}
