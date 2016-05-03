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

package org.farrukh.template.rest.inbound;

import org.farrukh.template.rest.domain.Book;
import org.farrukh.template.rest.domain.metadata.Response;
import org.farrukh.template.rest.exception.BookCreationError;
import org.farrukh.template.rest.feedback.RestFeedbackContext;
import org.farrukh.template.rest.service.CoreService;
import org.kurron.feedback.AbstractFeedbackAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class RestInboundGateway extends AbstractFeedbackAware {

    private final CoreService coreService;

    @Autowired
    public RestInboundGateway(CoreService coreService) {
        this.coreService = coreService;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<Response> createBook(@RequestBody final Book book) {
        try {
            Book createdBook = coreService.create(book);
            Response response = new Response();
            response.setBooks(Arrays.asList(createdBook));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new BookCreationError(RestFeedbackContext.SOME_FEEDBACK);
        }
    }

}
