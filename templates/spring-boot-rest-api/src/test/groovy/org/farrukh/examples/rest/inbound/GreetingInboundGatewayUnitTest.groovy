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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import com.fasterxml.jackson.databind.ObjectMapper
import org.farrukh.examples.rest.BaseUnitTest
import org.farrukh.examples.rest.core.CoreService
import org.farrukh.examples.rest.inbound.domain.Greeting
import org.farrukh.examples.rest.inbound.domain.Request
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/**
 * Unit level test for greeting controller.
 */
class GreetingInboundGatewayUnitTest extends BaseUnitTest {

    def coreService = Stub(CoreService)

    MockMvc mockMvc

    def mapper = new ObjectMapper()

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new GreetingInboundGateway(coreService)).
                build()
    }

    def 'should test successful retuning greeting'() {
        given: 'expected results'
        def expectedContent = '''{"payload":{"message":"Hello World!"}}'''
        def expectedStatusCode = HttpStatus.OK.value()
        def expectedMediaType = MediaType.APPLICATION_JSON_VALUE

        when: 'the method is called'
        def resultActions = mockMvc.perform(get('/greeting'))

        and: 'the http response is extracted'
        def mvcResult = resultActions.andReturn()
        def response = mvcResult.response

        then: 'expected results is returned'
        response.contentType == expectedMediaType
        response.status == expectedStatusCode
        response.contentAsString == expectedContent
    }

    def 'should test successful sending a message'() {
        given: 'expected results'
        def expectedMediaType = MediaType.APPLICATION_JSON_VALUE
        def expectedStatusCode = HttpStatus.OK.value()
        def expectedResponse = '''{"payload":{"message":"HELLO WORLD!"}}'''

        and: 'the request is created'
        def headers = new HttpHeaders()
        headers.setContentType(MediaType.valueOf(expectedMediaType))
        def request = new Request()
        request.payload = new Greeting(message: 'Hello World!')
        def content = mapper.writeValueAsString(request)

        and: 'the conversation is made'
        coreService.convert(!null as Greeting) >> {
            new Greeting(message: 'HELLO WORLD!')
        }

        when: 'the request is made'
        def resultActions = mockMvc.perform(post('/convert').
                headers(headers).
                content(content))

        and: 'the response is returned'
        def mvcResult = resultActions.andReturn()
        def response = mvcResult.response

        then:
        response.status == expectedStatusCode

        response.contentAsString == expectedResponse
    }

}
