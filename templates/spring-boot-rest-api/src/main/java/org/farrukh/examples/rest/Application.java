package org.farrukh.examples.rest;

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

}
