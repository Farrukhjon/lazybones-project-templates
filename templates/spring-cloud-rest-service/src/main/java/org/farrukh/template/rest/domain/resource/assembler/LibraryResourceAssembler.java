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

package org.farrukh.template.rest.domain.resource.assembler;

import org.farrukh.template.rest.domain.model.Library;
import org.farrukh.template.rest.domain.resource.LibraryResource;
import org.farrukh.template.rest.inbound.RestInboundGateway;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Farrukhjon D. Sattorov on 5/7/16.
 */
public class LibraryResourceAssembler extends ResourceAssemblerSupport<Library, LibraryResource> {

    public LibraryResourceAssembler() {
        super(RestInboundGateway.class, LibraryResource.class);
    }

    @Override
    public LibraryResource toResource(final Library library) {
        LibraryResource libraryResource = createResourceWithId(library.getId(), library);
        libraryResource.setLibrary(library);
        return libraryResource;
    }
}
