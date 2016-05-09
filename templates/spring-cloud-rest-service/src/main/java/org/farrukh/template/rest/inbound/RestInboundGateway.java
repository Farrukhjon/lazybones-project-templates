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
import org.farrukh.template.rest.domain.model.Library;
import org.farrukh.template.rest.domain.resource.IndexResource;
import org.farrukh.template.rest.domain.resource.LibraryResource;
import org.farrukh.template.rest.domain.resource.assembler.LibraryResourceAssembler;
import org.farrukh.template.rest.exception.LibraryCreationError;
import org.farrukh.template.rest.feedback.RestFeedbackContext;
import org.farrukh.template.rest.service.CoreService;
import org.kurron.feedback.AbstractFeedbackAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@ExposesResourceFor(LibraryResource.class)
@RestController
@RequestMapping(value = "/", produces = CustomMediaTypeHolder.JSON_MIME_TYPE)
public class RestInboundGateway extends AbstractFeedbackAware {

    private final CoreService coreService;

    private final LibraryResourceAssembler assembler;

    @Autowired
    public RestInboundGateway(final CoreService coreService,
                              final LibraryResourceAssembler assembler) {
        this.coreService = coreService;
        this.assembler = assembler;
    }

    @RequestMapping(value = "/libraries", method = RequestMethod.POST, consumes = CustomMediaTypeHolder.JSON_MIME_TYPE)
    public ResponseEntity<LibraryResource> createLibrary(@RequestBody final Library library) {
        try {
            Library createdLibrary = coreService.create(library);
            URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
            LibraryResource libraryResource = assembler.toResource(createdLibrary);
            return ResponseEntity.created(location).body(libraryResource);
        } catch (Exception e) {
            throw new LibraryCreationError(RestFeedbackContext.SOME_FEEDBACK);
        }
    }

    @RequestMapping("/libraries/{id}")
    public ResponseEntity<?> getLibrary(@PathVariable final String id) {
        Library library = coreService.getLibraryById(id);
        LibraryResource libraryResource = assembler.toResource(library);
        return ResponseEntity.ok(libraryResource);
    }

    @RequestMapping(value = "/libraries", method = RequestMethod.GET)
    public ResponseEntity<List<LibraryResource>> getLibraries() {
        List<Library> libraries = coreService.getLibraries();
        List<LibraryResource> libraryResources = assembler.toResources(libraries);
        return ResponseEntity.ok(libraryResources);
    }

}
