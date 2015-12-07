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

import org.farrukh.examples.rest.inbound.domain.Greeting;
import org.farrukh.examples.rest.inbound.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample, for experiments, rest controller.
 */
@RestController
public class GreetingInboundGateway implements InboundGateway {

    /**
     * Creates a response with greeting payload.
     * @return Response.
     */
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public ResponseEntity<Response<Greeting>> getGreeting() {
        Response<Greeting> response = new Response<>();
        Greeting greeting = new Greeting();
        greeting.setMessage("Hello World!");
        response.setPayload(greeting);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
