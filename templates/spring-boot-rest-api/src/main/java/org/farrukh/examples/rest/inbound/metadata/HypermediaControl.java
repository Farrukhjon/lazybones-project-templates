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
