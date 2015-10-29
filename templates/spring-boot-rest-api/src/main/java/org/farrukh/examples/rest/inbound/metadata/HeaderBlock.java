/*
 * Copyright (c) 2015. Farrukhjon D. Sattorov firedandy@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
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
