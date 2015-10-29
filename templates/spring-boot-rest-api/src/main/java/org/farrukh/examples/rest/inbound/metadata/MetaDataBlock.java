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
 * Optional meta-data section of the control.
 */
public class MetaDataBlock {

    /**
     * RFC 2046 MIME type.
     */
    @JsonProperty("mime-type")
    private String mimeType;

    /**
     * The size, in bytes, of the stored asset.
     **/
    @JsonProperty("content-length")
    private int contentLength;

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(final int contentLength) {
        this.contentLength = contentLength;
    }
}
