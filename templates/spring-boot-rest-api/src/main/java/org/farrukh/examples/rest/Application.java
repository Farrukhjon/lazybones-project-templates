package org.farrukh.examples.rest;

import org.farrukh.examples.rest.mvcconfig.CustomWebMvcConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
     * In order to just add an interceptor, and leave existing, default boot mvc configuration,
     * we just add the bean of the WebMvcConfigurerAdapter.
     * In this case we didn't need to create configuration class using @Configuration and EnableWebMvc.
     * Here we just kept default Spring Boot MVC features and added our own additional interceptor.
     * @return custom mvc configurer.
     */
    @Bean
    public WebMvcConfigurerAdapter customWebMvcConfigurerAdapter() {
        return new CustomWebMvcConfigurer();
    }

}
