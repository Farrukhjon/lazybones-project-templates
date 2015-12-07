/*
 * Copyright (c) 2015. Farrukhjon D. Sattorov firedandy@gmail.com.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.farrukh.examples.rest.inbound.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Greeting holder object.
 */
public class Greeting {

    /**
     * holds greeting message.
     */
    @JsonProperty
    private String message;

    /**
     * Gets the greeting message.
     * @return the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets a message.
     * @param message some message.
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}
