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
package org.farrukh.examples.rest.inbound.domain.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Response DTO.
 * Should warp a payload for REST client.
 * @param <T> payload type.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> extends ResourceSupport {

    /**
     * Holds some payload for this response.
     */
    @JsonProperty
    private T payload;

    /**
     * Gets a the payload.
     * @return the response payload.
     */
    public T getPayload() {
        return payload;
    }

    /**
     * Sets a payload.
     * @param payload some payload.
     */
    public void setPayload(final T payload) {
        this.payload = payload;
    }


}
