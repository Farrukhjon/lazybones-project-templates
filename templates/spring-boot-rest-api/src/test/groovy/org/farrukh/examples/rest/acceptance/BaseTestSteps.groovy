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
package org.farrukh.examples.rest.acceptance

import org.farrukh.examples.rest.Application
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.util.UriComponentsBuilder

/**
 * Base acceptance test.
 */
@ContextConfiguration(classes = Application, loader = SpringApplicationContextLoader)
@WebIntegrationTest(randomPort = true)
class BaseTestSteps {

    @Value('${local.server.port}')
    int port

    def createUrl(final String path) {
        UriComponentsBuilder.newInstance().
                scheme('http').
                host('localhost').
                port(port).
                path(path).
                build().
                toUriString()
    }

}
