package org.farrukh.examples.rest.inbound.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Describes an HTTP header required for a request.
 */
public class HeaderBlock {

    /**
     * Name of the required HTTP header.
     */
    @JsonProperty("header")
    private String header;

    /**
     * An example of a valid header value.
     */
    @JsonProperty("example")
    private String example;

    public String getHeader() {
        return header;
    }

    public void setHeader(final String header) {
        this.header = header;
    }

    public String getExample() {
        return example;
    }

    public void setExample(final String example) {
        this.example = example;
    }
}
