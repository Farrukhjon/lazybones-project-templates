package org.farrukh.examples.rest.mvcconfig;

import org.farrukh.examples.rest.interceptor.ContentTypeInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * If you want to take complete control of Spring MVC, you can add @Configuration and @EnableWebMvc on the class.
 */
public class CustomWebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new ContentTypeInterceptor());
    }
}
