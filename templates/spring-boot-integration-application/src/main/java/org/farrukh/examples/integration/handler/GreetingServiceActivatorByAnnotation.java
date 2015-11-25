package org.farrukh.examples.integration.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * The greeting service activator.
 */
public class GreetingServiceActivatorByAnnotation {

    /**
     * Logger.
     */
    @SuppressWarnings("constantname")
    private static final Logger logger = LoggerFactory.getLogger(GreetingServiceActivatorByAnnotation.class);

    /**
     * Just print out a given message.
     * @param greeting a message.
     * @return original message.
     */
    @ServiceActivator
    public String handleGreeting(final String greeting) {
        logger.info("The message {} was handled by {}", greeting, GreetingServiceActivatorByAnnotation.class);
        return greeting;
    }

}
