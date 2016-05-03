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

package org.farrukh.template.rest.outbound;

import org.farrukh.template.rest.domain.Book;
import org.farrukh.template.rest.repository.BookRepository;
import org.kurron.feedback.AbstractFeedbackAware;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public class MongoStorageOutboundGatewayImpl extends AbstractFeedbackAware implements StorageOutboundGateway {

    private final BookRepository bookRepository;

    public MongoStorageOutboundGatewayImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book store(final Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book retrieve(final String id) {
        return bookRepository.findOne(id);
    }

    @Cacheable("books")
    @Override
    public List<Book> retrieveAll() {
        return bookRepository.findAll();
    }

    @Override
    public void remove(final Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void remove(final String id) {
        bookRepository.delete(id);
    }

}
