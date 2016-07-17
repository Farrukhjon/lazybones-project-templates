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

import static org.farrukh.template.rest.domain.metadata.CustomMediaTypeHolder.JSON_MEDIA_TYPE

import org.farrukh.template.BaseInboundIntegrationTest
import org.farrukh.template.rest.domain.model.Author
import org.farrukh.template.rest.domain.model.Book
import org.farrukh.template.rest.domain.resource.LibraryResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity

class RestInboundGatewayIntegrationTests extends BaseInboundIntegrationTest {

    private String baseURL = '/libraries'

    def 'exercise happy path for creating a library'() {
        given: 'expected response data'
        def expectedResponseStatusCode = HttpStatus.CREATED

        and: 'proper request is created'
        def url = createUrl(baseURL)
        def book = new Book(name: 'Effective Java', authors: [new Author(firstName: 'Bloch', lastName: 'Joshua')])

        def headers = new HttpHeaders()
        headers.setContentType(JSON_MEDIA_TYPE)
        headers.setAccept([JSON_MEDIA_TYPE])
        def request = new RequestEntity<>(book, headers, HttpMethod.POST, url)

        when: 'the request is made'
        def response = restTemplate.exchange(request, LibraryResource)

        then: 'expected response should be returned'
        response.statusCode == expectedResponseStatusCode
    }

    def 'exercise happy path for retrieving a library'() {
        given:
        def url = createUrl(baseURL)

        and:
        def book = new Book(name: 'Effective Java', authors: [new Author(firstName: 'Bloch', lastName: 'Joshua')])
        def headers = new HttpHeaders()
        headers.setContentType(JSON_MEDIA_TYPE)
        headers.setAccept([JSON_MEDIA_TYPE])
        def tmpRequest = RequestEntity.post(url).
                accept(JSON_MEDIA_TYPE).
                contentType(JSON_MEDIA_TYPE).
                body(book)
        ResponseEntity tmpResponse = restTemplate.exchange(tmpRequest, LibraryResource)

        def url1 = tmpResponse.body.library.id
        and:
        RequestEntity request = RequestEntity.get(createUrl("/books/$url1")).
                accept(JSON_MEDIA_TYPE)

        when:
        def response = restTemplate.exchange(request, LibraryResource)

        then:
        response.statusCode == HttpStatus.OK
    }

}
