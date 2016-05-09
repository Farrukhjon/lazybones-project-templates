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

package org.farrukh.template.rest.inbound

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.farrukh.template.BaseUnitTest
import org.farrukh.template.rest.service.CoreService
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Shared

/**
 * Unit level tests against REST Inbound Gateway.
 */
class RestInboundGatewayUnitTests extends BaseUnitTest {

    def coreService = Stub(CoreService)

    MockMvc mockMvc

    @Shared
    ObjectMapper mapper

    def setupSpec() {
        mapper = new Jackson2ObjectMapperBuilder()
                .featuresToEnable(SerializationFeature.INDENT_OUTPUT)
                .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .build()
    }

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RestInboundGateway(coreService,
                                                                         indexResourceAssembler, assembler)).build()

    }

}
