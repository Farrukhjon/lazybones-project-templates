package org.farrukh.examples.batch;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Spring boot based application configuration binder with application.yml file.
 */
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
}
