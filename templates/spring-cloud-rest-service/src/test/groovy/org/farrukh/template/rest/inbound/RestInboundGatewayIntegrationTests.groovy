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

import org.farrukh.template.BaseInboundIntegrationTest
import org.farrukh.template.rest.domain.metadata.CustomMediaTypeHolder
import org.farrukh.template.rest.domain.model.Author
import org.farrukh.template.rest.domain.model.Book
import org.farrukh.template.rest.domain.resource.BookResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity

class RestInboundGatewayIntegrationTests extends BaseInboundIntegrationTest {

    def 'exercise happy path for creating a book'() {
        given: 'expected response data'
        def expectedResponseStatusCode = HttpStatus.CREATED

        and: 'proper request is created'
        def url = createUrl('/books')
        def book = new Book(name: 'Effective Java', authors: [new Author(firstName: 'Bloch', lastName: 'Joshua')])

        def headers = new HttpHeaders()
        headers.setContentType(CustomMediaTypeHolder.JSON_MEDIA_TYPE)
        headers.setAccept([CustomMediaTypeHolder.JSON_MEDIA_TYPE])
        def request = new RequestEntity<>(book, headers, HttpMethod.POST, url)

        when: 'the request is made'
        def response = restTemplate.exchange(request, BookResource)

        then: 'expected response should be returned'
        response.statusCode == expectedResponseStatusCode
    }

    def 'exercise happy path for retrieving a book'() {
        given:
        def url = createUrl('/books')

        and:
        def book = new Book(name: 'Effective Java', authors: [new Author(firstName: 'Bloch', lastName: 'Joshua')])
        def headers = new HttpHeaders()
        headers.setContentType(CustomMediaTypeHolder.JSON_MEDIA_TYPE)
        headers.setAccept([CustomMediaTypeHolder.JSON_MEDIA_TYPE])
        def tmpRequest = new RequestEntity<>(book, headers, HttpMethod.POST, url)
        def tmpResponse = restTemplate.exchange(tmpRequest, BookResource)

        and:
        def request = new RequestEntity<>(book, headers, HttpMethod.GET, createUrl("/books/${tmpResponse.body.book.id}"))

        when:
        def response = restTemplate.exchange(request, BookResource)

        then:
        response.statusCode == HttpStatus.OK
    }

}
