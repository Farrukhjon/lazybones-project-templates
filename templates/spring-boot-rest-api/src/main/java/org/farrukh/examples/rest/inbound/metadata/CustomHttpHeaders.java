package org.farrukh.examples.rest.inbound.metadata;

/**
 * Contains custom HTTP header names.
 */
public final class CustomHttpHeaders {

    /**
     * Private constructor, to prevent instantiation.
     */
    private CustomHttpHeaders() {

    }

    /**
     * The correlation id (a.k.a. work-unit) header, useful in stitching together work being done by the server.
     */
    public static final String X_CORRELATION_ID = "X-Correlation-Id";

    /**
     * The number of minutes to wait before expiring a given resource.
     */
    public static final String X_EXPIRATION_MINUTES = "X-Expiration-Minutes";

}
