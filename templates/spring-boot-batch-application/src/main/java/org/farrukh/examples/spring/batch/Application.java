package org.farrukh.examples.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot based stand-alone application.
 */
@SuppressWarnings({"checkstyle:hideutilityclassconstructor", "checkstyle:constantname"})
@EnableBatchProcessing
@SpringBootApplication
public class Application {

    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @SuppressWarnings("checkstyle:javadocmethod")
    public static void main(final String[] args) {
        logger.info("Running entry point application");
        SpringApplication.run(Application.class, args);
    }

}
