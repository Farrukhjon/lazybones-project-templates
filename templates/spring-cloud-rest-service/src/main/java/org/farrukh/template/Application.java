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

package org.farrukh.template;

import org.farrukh.template.rest.domain.resource.assembler.LibraryResourceAssembler;
import org.farrukh.template.rest.outbound.MongoStorageOutboundGatewayImpl;
import org.farrukh.template.rest.outbound.StorageOutboundGateway;
import org.farrukh.template.rest.outbound.repository.LibraryRepository;
import org.farrukh.template.rest.service.CoreService;
import org.farrukh.template.rest.service.CoreServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.RelProvider;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CoreService coreService(final StorageOutboundGateway storageOutboundGateway) {
        return new CoreServiceImpl(storageOutboundGateway);
    }

    @Bean
    public StorageOutboundGateway storageOutboundGateway(final LibraryRepository libraryRepository) {
        return new MongoStorageOutboundGatewayImpl(libraryRepository);
    }

    @Bean
    public LibraryResourceAssembler libraryResourceAssembler() {
        return new LibraryResourceAssembler();
    }

}
