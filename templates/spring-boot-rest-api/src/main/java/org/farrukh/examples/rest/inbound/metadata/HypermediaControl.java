package org.farrukh.examples.rest.inbound.metadata;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.MediaType;

/**
 * The hypermedia REST control for the example resource.
 * It can be serialized into both JSON and, potentially, XML.
 */
public class HypermediaControl extends ResourceSupport {

    /**
     * The JSON MIME type for the control.
     */
    public static final String JSON_MIME_TYPE = "application/json;type=example;version=1.0.0";

    /**
     * The XML MIME type for the control.
     */
    public static final String XML_MIME_TYPE = "application/xml;type=example;version=1.0.0";

    /**
     * Convenience form of the MIME-TYPE for Spring MVC APIs.
     **/
    public static final MediaType JSON_MEDIA_TYPE = MediaType.parseMediaType(JSON_MIME_TYPE);

    /**
     * Convenience form of the MIME-TYPE for Spring MVC APIs.
     **/
    public static final MediaType XML_MEDIA_TYPE = MediaType.parseMediaType(XML_MIME_TYPE);

}
