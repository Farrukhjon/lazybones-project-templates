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

package org.farrukh.template.rest.service;

import org.farrukh.template.rest.domain.model.Library;
import org.farrukh.template.rest.outbound.StorageOutboundGateway;
import org.kurron.feedback.AbstractFeedbackAware;

import java.util.List;

public class CoreServiceImpl extends AbstractFeedbackAware implements CoreService {

    private final StorageOutboundGateway storageOutboundGateway;

    public CoreServiceImpl(final StorageOutboundGateway storageOutboundGateway) {
        this.storageOutboundGateway = storageOutboundGateway;
    }

    @Override
    public Library create(final Library book) {
        return storageOutboundGateway.store(book);
    }

    @Override
    public Library getLibraryById(final String id) {
        return storageOutboundGateway.retrieve(id);
    }

    @Override
    public List<Library> getLibraries() {
        return storageOutboundGateway.retrieveAll();
    }

}
