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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.farrukh.template.rest.domain.Book;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * Response DTO.
 * Should warp a payload for REST client.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response extends ResourceSupport {

    /**
     * Json MIME type for the response.
     */
    public static final String JSON_MIME_TYPE = "application/json;type=app-name;version=1.0.0";

    /**
     * Json media type type for the response.
     */
    public static final MediaType JSON_MEDIA_TYPE = MediaType.valueOf(JSON_MIME_TYPE);

    /**
     * Custom text MIME type for the requesting text data.
     */
    public static final String TEXT_MIME_TYPE = "text/plain;type=app-name;version=1.0.0";

    /**
     * Custom text Media type type for the requesting text data.
     */
    public static final MediaType TEXT_MEDIA_TYPE = MediaType.valueOf(TEXT_MIME_TYPE);

    /**
     * Holds some payload for this response.
     */
    @JsonProperty
    private List<Book> books;

    @JsonProperty("error")
    private ErrorBlock errorBlock;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(final List<Book> books) {
        this.books = books;
    }

    public ErrorBlock getErrorBlock() {
        return errorBlock;
    }

    public void setErrorBlock(final ErrorBlock errorBlock) {
        this.errorBlock = errorBlock;
    }
}
