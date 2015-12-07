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
package org.farrukh.examples.rest

import com.fasterxml.jackson.databind.ObjectMapper
import org.farrukh.examples.rest.category.OutboundIntegrationTestCategory
import org.junit.experimental.categories.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestOperations
import org.springframework.web.util.UriComponentsBuilder

/**
 * Base integration test class based on spock specification and spring testing fetures.
 */
@Category(OutboundIntegrationTestCategory)
@WebIntegrationTest(randomPort = true)
@ContextConfiguration(classes = Application, loader = SpringApplicationContextLoader)
abstract class BaseInboundIntegrationTest extends BaseTest {

    @Autowired
    protected RestOperations restTemplate

    @Autowired
    protected ObjectMapper objectMapper

    @Value('${local.server.port}')
    int port

    /**
     * Creates uri for controllers tests.
     * @param path the request mapped path
     * @return uri in string.
     */
    String createUrl(String path) {
        UriComponentsBuilder.newInstance().
                scheme('http').
                host('localhost').
                port(port).
                path(path).
                build().
                toUriString()
    }

}
