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

import org.farrukh.template.rest.domain.model.Library;
import org.farrukh.template.rest.outbound.repository.LibraryRepository;
import org.kurron.feedback.AbstractFeedbackAware;

import java.util.List;

public class MongoStorageOutboundGatewayImpl extends AbstractFeedbackAware implements StorageOutboundGateway {

    private final LibraryRepository libraryRepository;

    public MongoStorageOutboundGatewayImpl(final LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public Library store(final Library library) {
        return libraryRepository.save(library);
    }

    @Override
    public Library retrieve(final String id) {
        return libraryRepository.findOne(id);
    }

    /*@Cacheable("librarys")*/
    @Override
    public List<Library> retrieveAll() {
        return libraryRepository.findAll();
    }

    @Override
    public void modify(final Library library, final String id) {
        Library libraryToUpdate = libraryRepository.findOne(id);
        libraryToUpdate.setName(library.getName());
        libraryRepository.save(libraryToUpdate);
    }

    @Override
    public void remove(final Library library) {
        libraryRepository.delete(library);
    }

    @Override
    public void remove(final String id) {
        libraryRepository.delete(id);
    }

}
