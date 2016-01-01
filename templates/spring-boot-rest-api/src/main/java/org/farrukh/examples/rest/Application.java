/*
 * Copyright (c) 2015. Farrukhjon D. Sattorov firedandy@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.farrukh.examples.rest;

import org.farrukh.examples.rest.outbound.DefaultOutboundGateway;
import org.farrukh.examples.rest.outbound.repository.ResourceRepository;
import org.farrukh.examples.rest.outbound.StorageOutboundGateway;
import org.kurron.feedback.FeedbackAwareBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Spring boot entry point class.
 */
@SuppressWarnings({"finalparameters", "javadocmethod", "linelength"})
@EnableConfigurationProperties(ApplicationProperties.class)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    /**
     * Returns rest template.
     *
     * @return The bean which is instance of RestTemplate.
     */
    @Bean
    public RestOperations restTemplate() {
        return new RestTemplate();
    }

    /**
     * Indicates the type of service emitting the messages.
     */
    @Value("${info.app.name}")
    private String serviceCode;

    /**
     * Indicates the instance of the service emitting the messages.
     */
    @Value("${PID}")
    private String serviceInstance;

    /**
     * Indicates the logical group of the service emitting the messages.
     */
    @Value("${info.app.realm}")
    private String realm;

    /**
     * Creates feedback been post processor.
     *
     * @return the feedback bean post processor.
     */
    @Bean
    public FeedbackAwareBeanPostProcessor feedbackAwareBeanPostProcessor() {
        return new FeedbackAwareBeanPostProcessor(serviceCode, serviceInstance, realm);
    }

    /**
     * Creates the storage bean.
     *
     * @param repository the repository.
     * @return the bean.
     */
    @Bean
    public StorageOutboundGateway storageOutboundGateway(final ResourceRepository repository) {
        return new DefaultOutboundGateway(repository);
    }

    /**
     * In order to just add an interceptor, and leave existing, default boot mvc configuration,
     * we just add the bean of the WebMvcConfigurerAdapter.
     * In this case we didn't need to create configuration class using @Configuration and EnableWebMvc.
     * Here we just kept default Spring Boot MVC features and added our own additional interceptor.
     * @return custom mvc configurer.
     *//*
    @Bean
    public WebMvcConfigurerAdapter customWebMvcConfigurerAdapter() {
        return new CustomWebMvcConfigurer();
    }*/

}
