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
package org.farrukh.examples.rest.inbound

import org.farrukh.examples.rest.BaseInboundIntegrationTest
import org.farrukh.examples.rest.inbound.domain.Greeting
import org.farrukh.examples.rest.inbound.domain.Response
import org.springframework.http.HttpHeaders

/**
 * Experimental test for requesting greeting message.
 */
class GreetingInboundGatewayIntegrationTest extends BaseInboundIntegrationTest {

    def 'exercise success requesting a greeting'() {
        given:
        def expectedMessage = 'Hello World!'
        def path = 'greeting'
        def url = createUrl(path)

        and:
        def headers = new HttpHeaders()
        headers.add('Content-Type', 'application/json')

        when:
        def response = restTemplate.getForEntity(url, Response, headers)

        def greeting = response.body.payload as Greeting
        then:
        response
        response.body
        greeting.message == expectedMessage
    }
}
