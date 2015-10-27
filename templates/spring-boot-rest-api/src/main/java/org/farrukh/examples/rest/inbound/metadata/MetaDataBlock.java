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
