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
import org.farrukh.examples.rest.BaseUnitTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/**
 * Unit level test for greeting controller.
 */
class GreetingInboundGatewayUnitTest extends BaseUnitTest {

    MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new GreetingInboundGateway()).build()
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

}