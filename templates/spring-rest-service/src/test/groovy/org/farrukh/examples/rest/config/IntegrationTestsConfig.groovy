package org.farrukh.examples.rest.config

import org.springframework.boot.test.TestRestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestOperations

/**
 * Configuration for the REST integration tests.
 */
@Configuration
class IntegrationTestsConfig {

    @Bean
    RestOperations restTemplate() {
        new TestRestTemplate()
    }

}
