/*
 * Copyright (c) 2015. Farrukhjon D. Sattorov firedandy@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.farrukh.examples.rest.acceptance

import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import groovy.util.logging.Slf4j
import org.farrukh.examples.rest.inbound.domain.Greeting
import org.farrukh.examples.rest.inbound.domain.Request
import org.farrukh.examples.rest.inbound.domain.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestOperations

/**
 * Class for implementing the acceptance tests scenarios.
 */
@Slf4j
class TestSteps extends BaseTestSteps {

    @Autowired
    RestOperations testRestTemplate

    /**
     * Used for created shared state for the steps methods.
     */
    class TestWorld {
        Request<Greeting> request
        def headers = new HttpHeaders()
        ResponseEntity<Response<Greeting>> response
        String url
    }

    /**
     * Shared state.
     */
    TestWorld testWorld

    @Before
    void createSharedState() {
        log.info('Creating the shared state')
        testWorld = new TestWorld()
        assert testRestTemplate
    }

    @After
    void destroySharedState() {
        log.info('Destroying the shared state')
        testWorld = null;
    }

    @Given('^The greeting url$')
    void 'The greeting url'() throws Throwable {
        testWorld.url = createUrl('/greeting')
    }

    @And('^The proper request is created$')
    void 'The proper request is created'() throws Throwable {
        //testWorld.request = new HttpEntity<>()
    }

    @When('^The client is called the get method using the created request$')
    void 'The client is called the get method using the created request'() throws Throwable {
        def url = testWorld.url
        def headers = testWorld.headers
        def response = testRestTemplate.getForEntity(url, Response, headers)
        testWorld.response = response
    }

    @Then('^The greeting REST inbound gateway returns the response$')
    void 'The greeting REST inbound gateway returns the response'() throws Throwable {
        def response = testWorld.response
        assert response
    }

    @And('^The Hello World! is returned upon response$')
    void 'The Hello world is returned upon response'() throws Throwable {
        def expectedMessage = 'Hello World!'
        def response = testWorld.response
        def greeting = response.body.payload as Greeting
        assert response.body
        assert greeting.message == expectedMessage
    }

    @And('^The proper headers is returned$')
    void 'The proper headers is returned'() throws Throwable {
        def expectedMediaType = MediaType.valueOf('application/json;charset=UTF-8')
        def response = testWorld.response
        def headers = response.headers
        assert headers.getContentType() == expectedMediaType
    }
}
