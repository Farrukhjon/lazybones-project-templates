/*
 * Copyright (c) 2015. Farrukhjon D. Sattorov firedandy@gmail.com.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.farrukh.template.rest.domain.metadata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Responsible for holding an error information.
 */
@Getter
public class ErrorBlock {

    /**
     * The error code.
     */
    @JsonProperty("code")
    private final int code;

    /**
     * The error message.
     */
    @JsonProperty("message")
    private final String message;

    @JsonCreator
    public ErrorBlock(final int code,
                      final String message) {
        this.code = code;
        this.message = message;
    }

}
