package org.farrukh.examples.integration.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Hello world domain object.
 */
public class Greeting {

    /**
     * Simple hello world greeting.
     */
    @JsonProperty
    private String greeting;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(final String greeting) {
        this.greeting = greeting;
    }
}
