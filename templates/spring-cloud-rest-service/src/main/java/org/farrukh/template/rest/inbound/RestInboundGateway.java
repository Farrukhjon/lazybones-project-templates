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

import org.farrukh.template.rest.domain.metadata.CustomMediaTypeHolder;
import org.farrukh.template.rest.domain.model.Book;
import org.farrukh.template.rest.domain.resource.BookResource;
import org.farrukh.template.rest.domain.resource.BookResourceAssembler;
import org.farrukh.template.rest.exception.BookCreationError;
import org.farrukh.template.rest.feedback.RestFeedbackContext;
import org.farrukh.template.rest.service.CoreService;
import org.kurron.feedback.AbstractFeedbackAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/", produces = CustomMediaTypeHolder.JSON_MIME_TYPE)
public class RestInboundGateway extends AbstractFeedbackAware {

    private final CoreService coreService;

    private final BookResourceAssembler assembler;

    @Autowired
    public RestInboundGateway(final CoreService coreService, final BookResourceAssembler assembler) {
        this.coreService = coreService;
        this.assembler = assembler;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST, consumes = CustomMediaTypeHolder.JSON_MIME_TYPE)
    public ResponseEntity<BookResource> createBook(@RequestBody final Book book) {
        try {
            Book createdBook = coreService.create(book);
            URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
            BookResource bookResource = assembler.toResource(createdBook);
            return ResponseEntity.created(location).body(bookResource);
        } catch (Exception e) {
            throw new BookCreationError(RestFeedbackContext.SOME_FEEDBACK);
        }
    }

    @RequestMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable final String id) {
        Book book = coreService.getBookById(id);
        BookResource bookResource = assembler.toResource(book);
        return ResponseEntity.ok(bookResource);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<BookResource>> getBooks() {
        List<Book> books = coreService.getBooks();
        List<BookResource> bookResources = assembler.toResources(books);
        return ResponseEntity.ok(bookResources);
    }

}
