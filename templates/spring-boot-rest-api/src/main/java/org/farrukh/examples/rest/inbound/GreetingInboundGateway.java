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

package org.farrukh.examples.rest.inbound;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.farrukh.examples.rest.core.CoreService;
import org.farrukh.examples.rest.feedback.RestFeedbackContext;
import org.farrukh.examples.rest.inbound.domain.Greeting;
import org.farrukh.examples.rest.inbound.domain.metadata.Request;
import org.farrukh.examples.rest.inbound.domain.metadata.Response;
import org.kurron.feedback.AbstractFeedbackAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Sample, for experiments, rest controller.
 */
@RestController
public class GreetingInboundGateway extends AbstractFeedbackAware implements InboundGateway {

    /**
     * Provides service algorithms.
     */
    private final CoreService coreService;

    @Autowired
    public GreetingInboundGateway(final CoreService coreService) {
        this.coreService = coreService;
    }

    /**
     * Creates a response with greeting payload.
     *
     * @return Response.
     */
    @RequestMapping(value = "/greeting", method = GET)
    public ResponseEntity<Response<Greeting>> getGreeting() {
        Response<Greeting> greetingResponse = new Response<>();
        Greeting greeting = new Greeting();
        greeting.setMessage("Hello World!");
        greetingResponse.setPayload(greeting);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        getFeedbackProvider().sendFeedback(RestFeedbackContext.GREETING_FEEDBACK);
        return new ResponseEntity<>(greetingResponse, headers, HttpStatus.OK);
    }

    /**
     * Sends a message back to the service.
     *
     * @param request the request.
     * @param headers the request headers.
     * @return the http status code.
     */
    @RequestMapping(value = "/convert", method = POST)
    public ResponseEntity<Response<Greeting>> postGreeting(@RequestBody final Request<Greeting> request,
                                                           @RequestHeader final HttpHeaders headers) {
        validateHeaders(headers);
        Greeting greeting = request.getPayload();
        Greeting converted = coreService.convert(greeting);
        Response<Greeting> response = new Response<>();
        response.setPayload(converted);
        ControllerLinkBuilder linkTo = linkTo(methodOn(GreetingInboundGateway.class).postGreeting(request, headers));
        response.add(linkTo.withSelfRel());
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setLocation(linkTo.toUri());
        return new ResponseEntity<>(response, responseHeader, HttpStatus.OK);
    }

    /**
     * Verifies the request headers content type and validates it.
     *
     * @param headers the request headers.
     */
    private void validateHeaders(final HttpHeaders headers) {
        long contentLength = headers.getContentLength();
        if (contentLength <= 0) {
            getFeedbackProvider().sendFeedback(RestFeedbackContext.WRONG_CONTENT_LENGTH);
        }
    }

}
