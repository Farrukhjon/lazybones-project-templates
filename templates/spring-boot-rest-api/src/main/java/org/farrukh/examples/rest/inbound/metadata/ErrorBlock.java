package org.farrukh.examples.rest.inbound.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Optional error section of the control.
 */
public class ErrorBlock {

    /**
     * Number uniquely describing the error conditions.
     */
    @JsonProperty
    private int code;

    /**
     * Details the error condition.
     **/
    @JsonProperty
    private String message;

    /**
     * Details the error condition in language targeted towards the developer.
     */
    @JsonProperty("developer-message")
    private String developerMessage;

    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(final String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
