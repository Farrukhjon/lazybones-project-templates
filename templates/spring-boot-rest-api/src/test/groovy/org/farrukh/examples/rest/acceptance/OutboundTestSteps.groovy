/*
 * Copyright (c) 2015. Farrukhjon D. Sattorov firedandy@gmail.com.
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
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
import org.farrukh.examples.rest.outbound.StorageOutboundGateway
import org.farrukh.examples.rest.outbound.domain.Resource
import org.springframework.beans.factory.annotation.Autowired

@Slf4j
class OutboundTestSteps extends BaseTestSteps {

    @Autowired
    StorageOutboundGateway storageOutboundGateway

    class TestWorld {
        Resource resource
        String uuidOfTheStoredResource
    }

    TestWorld testWorld

    @Before
    void initSharedState() {
        log.info('Creating the shared state')
        testWorld = new TestWorld()
    }

    @After
    void destroySharedState() {
        log.info('Destroying the shared state')
        testWorld = null
    }

    @Given('^The resources$')
    public void The_resources() throws Throwable {
        byte[] payload = 'Some data'.bytes
        def resource = new Resource(payload: payload)
        resource.id = UUID.randomUUID().toString()
        testWorld.resource = resource
        testWorld.uuidOfTheStoredResource = resource.id
    }

    @And('^The storage outbound gateway$')
    public void The_storage_outbound_gateway() throws Throwable {
        assert storageOutboundGateway
    }

    @When('^The storage outbound gateway store method is called$')
    public void The_storage_outbound_gateway_store_method_is_called() throws Throwable {
        def resource = testWorld.resource
        storageOutboundGateway.store(resource)
    }

    @Then('^The resource will be stored$')
    public void The_resource_will_be_stored() throws Throwable {
        def uuidOfTheStoredResource = testWorld.uuidOfTheStoredResource
        def retrievedResource = storageOutboundGateway.retrieve(uuidOfTheStoredResource)
        assert retrievedResource.payload == 'Some data'.bytes
    }
}
