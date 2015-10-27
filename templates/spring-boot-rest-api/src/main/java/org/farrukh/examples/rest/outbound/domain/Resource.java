package org.farrukh.examples.rest.outbound.domain;

/**
 * Some resource which should be stored and retrieved from outbound gateway.
 */
public class Resource {

    /**
     * The content type associated with the resource.
     */
    private String contentType;

    /**
     * The resource int bytes.
     */
    private byte[] payload;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(final byte[] payload) {
        this.payload = payload;
    }
}
