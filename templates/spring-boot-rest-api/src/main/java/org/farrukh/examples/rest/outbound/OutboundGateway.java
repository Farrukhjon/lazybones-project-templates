package org.farrukh.examples.rest.outbound;

import org.farrukh.examples.rest.outbound.domain.Resource;

import java.util.UUID;

/**
 * Base interfaces for storage outbound gateways.
 */
public interface OutboundGateway {

    /**
     * Stores some resource to a outbound gateway.
     *
     * @param resource - some resource.
     * @return UUID of the resource.
     */
    UUID store(final Resource resource);

    /**
     * Retrieves a resources from outbound gateway using id.
     *
     * @param resourceId - an id of the resource.
     * @return Some resource.
     */
    Resource retrieve(final UUID resourceId);

}
