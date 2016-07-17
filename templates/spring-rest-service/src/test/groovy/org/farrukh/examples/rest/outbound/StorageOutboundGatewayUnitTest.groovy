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
package org.farrukh.examples.rest.outbound

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.farrukh.examples.rest.BaseUnitTest
import org.farrukh.examples.rest.outbound.domain.Resource
import org.farrukh.examples.rest.outbound.repository.ResourceRepository

/**
 * Unit level test for the storage outbound gateway.
 */
class StorageOutboundGatewayUnitTest extends BaseUnitTest {

    def repository = Stub(ResourceRepository)
    def sut = new DefaultOutboundGateway(repository)

    def 'exercise happy path for storing a resource'() {
        given: 'expected results'
        byte[] expectedPayload = 'Hello world'.bytes
        def expectedId = UUID.randomUUID().
                toString()
        def expectedContentType = APPLICATION_JSON_VALUE

        and: 'the resource for storing is created'
        def resource = new Resource(id: expectedId, contentType: expectedContentType, payload: expectedPayload)

        and: 'the stubbed save is called'
        repository.save(!null as Resource) >> resource

        when: 'the store is invoked'
        def result = sut.store(resource)

        then: 'expected results are returned'
        result.id == expectedId
        result.contentType == expectedContentType
        result.payload == expectedPayload
    }

    def 'exercise happy path for retrieving a resource'() {
        given: 'expected results'
        byte[] expectedPayload = 'Hello world'.bytes
        def expectedId = UUID.randomUUID().toString()
        def expectedContentType = APPLICATION_JSON_VALUE

        and: 'the resource for storing is created'
        def resource = new Resource(id: expectedId, contentType: expectedContentType, payload: expectedPayload)

        and: 'the stubbed findOne is called'
        repository.findOne(!null as String) >> resource

        when: 'the retrieving by id is invoked'
        def result = sut.retrieve(expectedId)

        then: 'expected results are returned'
        result.id == expectedId
        result.contentType == expectedContentType
        result.payload == expectedPayload
    }

}
