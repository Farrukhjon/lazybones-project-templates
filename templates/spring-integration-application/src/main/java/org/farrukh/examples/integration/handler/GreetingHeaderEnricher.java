package org.farrukh.examples.integration.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;

/**
 * Header enricher endpoint.
 */

public class GreetingHeaderEnricher {

    /**
     * SLF4j based logger.
     */
    @SuppressWarnings("constantname")
    private static final Logger logger = LoggerFactory.getLogger(GreetingHeaderEnricher.class);

    /**
     * Handles a message in order to enrich its header.
     * @param greetingMessage a messge.
     * @return media type.
     */
    public MediaType handleHeaders(final Message<?> greetingMessage) {
        String contentType = (String) greetingMessage.getHeaders().get(AmqpHeaders.CONTENT_TYPE);
        MediaType mediaType = MediaType.valueOf(contentType);
        logger.info("The header content type of the message {} has been enriched", greetingMessage);
        return mediaType;
    }


}
