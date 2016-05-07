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

package org.farrukh.template

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.experimental.categories.Category
import org.kurron.categories.InboundIntegrationTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.web.client.RestOperations
import org.springframework.web.util.UriComponentsBuilder

@Category(InboundIntegrationTest)
@WebIntegrationTest(randomPort = true)
abstract class BaseInboundIntegrationTest extends BaseIntegrationTest {

    @Autowired
    RestOperations restTemplate

    @Autowired
    ObjectMapper objectMapper

    @Value('${local.server.port}')
    int port

    /**
     * Creates uri for controllers tests.
     * @param path the request mapped path
     * @return uri in string.
     */
    def createUrl(String path) {
        UriComponentsBuilder.newInstance().
                scheme('http').
                host('localhost').
                port(port).
                path(path).
                build().
                toUri()
    }

}
